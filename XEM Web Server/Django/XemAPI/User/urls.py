from django.conf.urls import url
from rest_framework.urlpatterns import format_suffix_patterns
from User.views import (
	UserDetailAPIView,
	UserUpdateAPIView,
	UserDeleteAPIView,
	UserListCreateAPIView,
	)

urlpatterns = format_suffix_patterns([
    url(r'^api/users/$', UserListCreateAPIView.as_view(), name='user-list-create'),
    url(r'^api/users/(?P<pk>\d+)/$', UserDetailAPIView.as_view(), name='user-detail'),
    url(r'^api/users/(?P<pk>\d+)/edit/$', UserUpdateAPIView.as_view(), name='user-update'),
    url(r'^api/users/(?P<pk>\d+)/delete/$', UserDeleteAPIView.as_view(), name='user-delete'),
    ])
