from .models import Location
from .views import *
# from .views import LocationDetailApiView
from django.conf.urls import url
from rest_framework.urlpatterns import format_suffix_patterns

urlpatterns = format_suffix_patterns([
    url(r'^api/locations/$',LocationListCreateApiView.as_view(),name='Location-List-Create'),
    url(r'^api/locations/(?P<location_uuid>[0-9a-f-]+)/$', LocationDetailApiView.as_view(), name='Location-Detail'),
    url(r'^api/locations/(?P<location_uuid>[0-9a-f-]+)/delete/$', LocationDeleteAPIView.as_view(), name='Location-Delete'),
    url(r'^api/locations/(?P<location_uuid>[0-9a-f-]+)/edit/$', LocationUpdateAPIView.as_view(), name='Location-Edit')
    # url(r'^api/locationss/(?P<location_uuid>[a-f0-9]{8}-?[a-f0-9]{4}-?4[a-f0-9]{3}-?[89ab][a-f0-9]{3}-?[a-f0-9]{12})/$', LocationDetailApiView.as_view(), name='Location-Detail')
])
