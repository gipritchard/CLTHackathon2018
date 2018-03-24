from rest_framework import viewsets, permissions, status
from rest_framework.decorators import detail_route, list_route, permission_classes
from rest_framework.pagination import PageNumberPagination
from rest_framework.response import Response
from django.shortcuts import get_object_or_404
import models, serializers 
from braces.views import CsrfExemptMixin
from django.db.models import Q
from json import loads

def str2bool(v):
    return str(v).lower() in ("yes", "true", "t", "1")

class GeoObjectViewSet(viewsets.ModelViewSet):
    serializer_class = serializers.GeoCereal
    queryset = models.GeoObject.objects.all()
    lookup_field = ('pk')

    def get_queryset(self):
        since = self.request.query_params.get('since', None)
        search = self.request.query_params.get('search', None)
        t = self.request.query_params.get('obj_type', None)
        sub_t = self.request.query_params.get('sub_type', None)
        queryset = models.GeoObject.objects.all()
        
        if since is not None:
            last_time = datetime.strptime(since, '%Y-%m-%d %H:%M:%S')
            queryset = queryset.filter(date_edited__gte=last_time)
        if search is not None:
            queryset = queryset.filter(name__icontains = search).order_by('pk')
        if t is not None:
            queryset = queryset.filter(obj_type__icontains = t).order_by('pk')
        if sub_t is not None:
            queryset = queryset.filter(sub_type__icontains = sub_t).order_by('pk')
            
        return queryset