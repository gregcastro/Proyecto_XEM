from .models import Rig
from .views import *
# from .views import RigDetailApiView
from django.conf.urls import url
from rest_framework.urlpatterns import format_suffix_patterns

urlpatterns = format_suffix_patterns([
    url(r'^api/rigs/$',RigListCreateApiView.as_view(),name='Rig-List-Create'),
    url(r'^api/rigs/(?P<rig_uuid>[0-9a-f-]+)/$', RigDetailApiView.as_view(), name='Rig-Detail'),
    url(r'^api/rigs/(?P<rig_uuid>[0-9a-f-]+)/delete/$', RigDeleteAPIView.as_view(), name='Rig-Delete'),
    url(r'^api/rigs/(?P<rig_uuid>[0-9a-f-]+)/edit/$', RigUpdateAPIView.as_view(), name='Rig-Edit')
    # url(r'^api/rigss/(?P<rig_uuid>[a-f0-9]{8}-?[a-f0-9]{4}-?4[a-f0-9]{3}-?[89ab][a-f0-9]{3}-?[a-f0-9]{12})/$', RigDetailApiView.as_view(), name='Rig-Detail')
])
