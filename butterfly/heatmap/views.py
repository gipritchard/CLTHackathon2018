# -*- coding: utf-8 -*-
from __future__ import unicode_literals

from django.shortcuts import render, reverse
from django.contrib import messages
from django.http import HttpResponseRedirect
from csv import reader
import json
from models import GeoObject, Location, LocationArea
from django.views.generic import (TemplateView, FormView, RedirectView,
                                  UpdateView, CreateView, DeleteView)

# Create your views here.
class Landing(TemplateView):
    def get_template_names(self):
        return ['main.html']

class Payload(object):
    def __init__(self, j):
        self.__dict__ = json.loads(j)

class Testing(TemplateView):
    def get_template_names(self):
        return ['main.html']

    def get_context_data(self, **kwargs):
        context = super(Testing, self).get_context_data(**kwargs)
        for poo in GeoObject.objects.filter(obj_type="Price"):
            # try:
            #     new_lat = poo.location.points.all()[0].longitude
            #     new_long = poo.location.points.all()[0].latitude
            #     point = poo.location.points.all()[0]
            #     point.latitude = new_lat
            #     point.longitude = new_long
            #     point.save()
            # except Exception, e: pass
            try:
                for p in poo.location.points.all():
                    new_lat = p.longitude
                    new_long = p.latitude
                    p.latitude = new_lat
                    p.longitude = new_long
                    p.save()
            except Exception, e: pass
        return context

def upload_csv(request):
    data = {}
    if "GET" == request.method:
        return render(request, "upload.html", data)
    # if not GET, then proceed
    try:
        csv_file = request.FILES["csv_file"]
        if not csv_file.name.endswith('.csv'):
            messages.error(request,'File is not CSV type')
            return HttpResponseRedirect(reverse("upload_csv"))
        #if file is too large, return
        if csv_file.multiple_chunks():
            messages.error(request,"Uploaded file is too big (%.2f MB)." % (csv_file.size/(1000*1000),))
            return HttpResponseRedirect(reverse("upload_csv"))
 
        file_data = csv_file.read().decode("utf-8")        
 
        #lines = file_data.split("\n")
        #loop over the lines and save them in db. If error , store as string and then display
        for fields in reader(csv_file):
            try:
                create_price(fields)
            except Exception as e:
                print repr(e)               
                pass
 
    except Exception as e:
        print "Unable to upload file. "+repr(e)
        messages.error(request,"Unable to upload file. "+repr(e))
 
    return HttpResponseRedirect(reverse("upload_csv"))

def upload_json(request):
    data = {}
    if "GET" == request.method:
        return render(request, "upload.html", data)
    # if not GET, then proceed
    try:
        csv_file = request.FILES["csv_file"]
        if not csv_file.name.endswith('json'):
            messages.error(request,'File is not JSON type')
            return HttpResponseRedirect(reverse("upload_json"))
        #if file is too large, return
        if csv_file.multiple_chunks():
            messages.error(request,"Uploaded file is too big (%.2f MB)." % (csv_file.size/(1000*1000),))
            return HttpResponseRedirect(reverse("upload_json"))     
 
        #lines = file_data.split("\n")
        p = Payload(csv_file.read().decode("utf-8")  )
        for f in p.features:
            create_park(f)
    except Exception as e:
        print "Unable to upload file. "+repr(e)
        messages.error(request,"Unable to upload file. "+repr(e))
 
    return HttpResponseRedirect(reverse("upload_json"))

def create_school(field):
    print field
    form = GeoObject(
        name = field[1],
        latitude = field[12],
        longitude = field[13],
        obj_type = "School",
        sub_type = "Location"
    )
    print form
    form.save()  

def create_price(field):
    print field
    avg_nums = [field[1], field[2], field[3], field[4], field[5]]
    L = [int(n) for n in avg_nums if n]
    form = GeoObject(
        name = field[0],
        obj_type = "Price",
        value = "%s" % (str(sum(L)/len(L) if L else '0'))
    )
    print form
    form.save()  

def add_points_to_price(field):
    print field
    geo = GeoObject.objects.filter(name=field['zip'])[0]
    print geo
    a = LocationArea(name="Polygon")
    a.save()
    for p in field['polygon']:
        p = Location(latitude=p[0], longitude=p[1])
        p.save()
        a.points.add(p)
    a.save()
    geo.location = a
    geo.save()

def create_hospital(feature):
    print feature
    o = GeoObject(
        name = feature['properties']['name'],
        obj_type = 'Hospital',
        extra = feature['properties']['zipcode']
    )
    o.save()
    p = Location(latitude=feature['geometry']['coordinates'][0], 
        longitude=feature['geometry']['coordinates'][1])
    p.save()
    a = LocationArea(name="Point")
    a.save()
    a.points.add(p)
    o.location = a 
    o.save()

def create_park(feature):
    print feature
    o = GeoObject(
        name = feature['properties']['name'],
        obj_type = 'Parks',
        extra = feature['properties']['zipcode']
    )
    o.save()
    p = Location(latitude=feature['geometry']['coordinates'][0], 
        longitude=feature['geometry']['coordinates'][1])
    p.save()
    a = LocationArea(name="Point")
    a.save()
    a.points.add(p)
    o.location = a 
    o.save()

def create_library(feature):
    print feature
    o = GeoObject(
        name = feature['properties']['name'],
        obj_type = 'Library'
    )
    o.save()
    p = Location(latitude=feature['geometry']['coordinates'][0], 
        longitude=feature['geometry']['coordinates'][1])
    p.save()
    a = LocationArea(name="Point")
    a.save()
    a.points.add(p)
    o.location = a 
    o.save()

def create_greenway(feature):
    print feature
    o = GeoObject(
        name = feature['properties']['name'],
        obj_type = 'Greenway',
        extra = feature['properties']['type']
    )
    o.save()
    a = LocationArea(name=feature['geometry']['type'])
    a.save()
    for c in feature['geometry']['coordinates']:
        try:
            p = Location(latitude = c[0], longitude = c[1])
            p.save()
            a.points.add(p)
        except Exception, e: 
            pass
    o.location = a 
    o.save()