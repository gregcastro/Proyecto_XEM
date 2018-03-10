from django.shortcuts import render
from .models import Location
from .serializers import *
from rest_framework.response import Response
from rest_framework.generics import (ListCreateAPIView,DestroyAPIView, RetrieveAPIView, RetrieveUpdateAPIView)


# Create your views here.

class LocationListCreateApiView(ListCreateAPIView):
    queryset = Location.objects.all()
    serializer_class = LocationSerializer

class LocationDetailApiView(RetrieveAPIView):
    queryset = Location.objects.all()
    serializer_class = LocationSerializer
    lookup_field = 'location_uuid'

class LocationUpdateAPIView(RetrieveUpdateAPIView):
    queryset = Location.objects.all()
    serializer_class = LocationUpdateSerializer
    lookup_field = 'location_uuid'

class LocationDeleteAPIView(DestroyAPIView):
    queryset = Location.objects.all()
    serializer_class = LocationSerializer
    lookup_field = 'location_uuid'
