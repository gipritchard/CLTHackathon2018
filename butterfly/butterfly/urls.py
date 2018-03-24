"""butterfly URL Configuration

The `urlpatterns` list routes URLs to views. For more information please see:
    https://docs.djangoproject.com/en/1.11/topics/http/urls/
Examples:
Function views
    1. Add an import:  from my_app import views
    2. Add a URL to urlpatterns:  url(r'^$', views.home, name='home')
Class-based views
    1. Add an import:  from other_app.views import Home
    2. Add a URL to urlpatterns:  url(r'^$', Home.as_view(), name='home')
Including another URLconf
    1. Import the include() function: from django.conf.urls import url, include
    2. Add a URL to urlpatterns:  url(r'^blog/', include('blog.urls'))
"""
from django.conf.urls import url, include
from django.contrib import admin
from heatmap import views
from heatmap import api_urls

urlpatterns = [
    url(r'^admin/', admin.site.urls),
    url(r'^butterfly/v1', include(api_urls, namespace='heatmap_api')),
    url(r'^butterfly/upload', views.upload_csv, name="upload_csv"),
    url(r'^butterfly/json', views.upload_json, name="upload_json"),
    url(r'^butterfly/test', views.Testing.as_view(), name="test"),
    url(r'^butterfly', views.Landing.as_view(), name="landing"),
    url('^searchableselect/', include('searchableselect.urls')),
]
