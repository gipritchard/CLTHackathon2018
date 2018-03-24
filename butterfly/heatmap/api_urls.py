from django.conf.urls import include, url
from rest_framework import routers
import api

router = routers.DefaultRouter()
router.register(r'obj', api.GeoObjectViewSet)

urlpatterns = [
   url(r'^/', include(router.urls)),
]