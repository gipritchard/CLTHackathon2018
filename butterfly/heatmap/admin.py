# -*- coding: utf-8 -*-
from __future__ import unicode_literals

from django.contrib import admin
from django.contrib.auth.admin import UserAdmin
from django.utils.translation import ugettext_lazy as _
import models

@admin.register(models.Location)
class GeoObjectAdmin(admin.ModelAdmin):
    list_display = ('latitude', 'longitude')

@admin.register(models.LocationArea)
class GeoObjectAdmin(admin.ModelAdmin):
    list_display = ['name',]

@admin.register(models.GeoObject)
class GeoObjectAdmin(admin.ModelAdmin):
    list_display = ('name', 'latitude', 'longitude')