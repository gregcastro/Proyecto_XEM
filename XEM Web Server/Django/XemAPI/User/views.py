from django.shortcuts import render
from .models import User
from .serializers import *
from rest_framework.response import Response
from rest_framework.generics import (ListCreateAPIView,DestroyAPIView, RetrieveAPIView, RetrieveUpdateAPIView)


# Create your views here.

class UserListCreateAPIView(ListCreateAPIView):
    queryset = User.objects.all()
    serializer_class = UserSerializer

class UserDetailAPIView(RetrieveAPIView):
    queryset = User.objects.all()
    serializer_class = UserSerializer

class UserUpdateAPIView(RetrieveUpdateAPIView):
    queryset = User.objects.all()
    serializer_class = UserSerializer

class UserDeleteAPIView(DestroyAPIView):
    queryset = User.objects.all()
    serializer_class = UserSerializer
