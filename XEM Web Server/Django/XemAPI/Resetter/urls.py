from .models import Resetter
from .views import *
# from .views import ResetterDetailApiView
from django.conf.urls import url
from rest_framework.urlpatterns import format_suffix_patterns

urlpatterns = format_suffix_patterns([
    url(r'^api/resetters/$',ResetterListCreateApiView.as_view(),name='Resetter-List-Create'),
    url(r'^api/resetters/(?P<resetter_uuid>[0-9a-f-]+)/$', ResetterDetailApiView.as_view(), name='Resetter-Detail'),
    url(r'^api/resetters/(?P<resetter_uuid>[0-9a-f-]+)/delete/$', ResetterDeleteAPIView.as_view(), name='Resetter-Delete'),
    url(r'^api/resetters/(?P<resetter_uuid>[0-9a-f-]+)/edit/$', ResetterUpdateAPIView.as_view(), name='Resetter-Edit')
    # url(r'^api/resetterss/(?P<resetter_uuid>[a-f0-9]{8}-?[a-f0-9]{4}-?4[a-f0-9]{3}-?[89ab][a-f0-9]{3}-?[a-f0-9]{12})/$', ResetterDetailApiView.as_view(), name='Resetter-Detail')
])
