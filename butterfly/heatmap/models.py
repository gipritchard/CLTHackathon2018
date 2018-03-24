# -*- coding: utf-8 -*-
from __future__ import unicode_literals

from django.db import models

# Create your models here.
class Location(models.Model):
    latitude = models.DecimalField(decimal_places=8, max_digits=200)
    longitude = models.DecimalField(decimal_places=8, max_digits=200)

    def __unicode__(self):
        return '%s, %s' % (str(self.latitude), str(self.longitude))

#POINT, LINE, MULTILINE, POLYGON
class LocationArea(models.Model):
    name = models.CharField(max_length=255)
    points = models.ManyToManyField(Location, blank=True)

    def __unicode__(self):
        return '%s' % (self.name)

#SCHOOL, GREENWAY, HOSPITAL, LIBRARY, PARK,
class GeoObject(models.Model):
    name = models.CharField(max_length=255)
    latitude = models.DecimalField(decimal_places=8, max_digits=200, blank=True, null=True)
    longitude = models.DecimalField(decimal_places=8, max_digits=200, blank=True, null=True)
    location = models.ForeignKey(LocationArea, blank=True, null=True)
    obj_type = models.CharField(max_length=255)
    sub_type = models.CharField(max_length=255, blank=True, null=True)
    value = models.CharField(max_length=255, blank=True, null=True)
    extra = models.CharField(max_length=255, blank=True, null=True)

    date_edited = models.DateTimeField(auto_now=True)
    date_created = models.DateTimeField(auto_now_add=True)

    def __unicode__(self):
        return '%s (%s)' % (self.name, str(self.pk))