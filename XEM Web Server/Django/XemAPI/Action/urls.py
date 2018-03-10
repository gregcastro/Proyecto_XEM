from .models import Action
from .views import *
# from .views import ActionDetailApiView
from django.conf.urls import url
from rest_framework.urlpatterns import format_suffix_patterns

urlpatterns = format_suffix_patterns([
    url(r'^api/actions/$',ActionListCreateApiView.as_view(),name='Action-List-Create'),
    url(r'^api/actions/(?P<action_uuid>[0-9a-f-]+)/$', ActionDetailApiView.as_view(), name='Action-Detail'),
    url(r'^api/actions/(?P<action_uuid>[0-9a-f-]+)/delete/$', ActionDeleteAPIView.as_view(), name='Action-Delete'),
    url(r'^api/actions/(?P<action_uuid>[0-9a-f-]+)/edit/$', ActionUpdateAPIView.as_view(), name='Action-Edit')
    # url(r'^api/actionss/(?P<action_uuid>[a-f0-9]{8}-?[a-f0-9]{4}-?4[a-f0-9]{3}-?[89ab][a-f0-9]{3}-?[a-f0-9]{12})/$', ActionDetailApiView.as_view(), name='Action-Detail')
])
