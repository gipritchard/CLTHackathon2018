# Serializers define the API representation.
import models
from rest_framework import serializers
from datetime import datetime

class PointsField(serializers.RelatedField):
    def to_representation(self, value):
        return [ {'latitude': l.latitude, 'longitude': l.longitude} for l in value.points.all()]

class LocationCereal(serializers.ModelSerializer):
    class Meta:
        model = models.Location
        fields = ('pk', 'latitude', 'longitude')

class LocationAreaCereal(serializers.ModelSerializer):
    points = LocationCereal(many=True, read_only=True)

    class Meta:
        model = models.LocationArea
        fields = ('pk', 'name', 'points')

class GeoCereal(serializers.ModelSerializer):
    location = PointsField(many = False, read_only=True)

    class Meta:
        model = models.GeoObject
        fields = ('pk', 'name', 'latitude', 'longitude', 'location',
            'obj_type', 'sub_type', 'value', 'extra')