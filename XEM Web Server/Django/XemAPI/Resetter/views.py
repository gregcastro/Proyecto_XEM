from django.shortcuts import render
from .models import Resetter
from .serializers import *
from rest_framework.response import Response
from rest_framework.generics import (ListCreateAPIView,DestroyAPIView, RetrieveAPIView, RetrieveUpdateAPIView)


# Create your views here.

class ResetterListCreateApiView(ListCreateAPIView):
    queryset = Resetter.objects.all()
    serializer_class = ResetterSerializer

class ResetterDetailApiView(RetrieveAPIView):
    queryset = Resetter.objects.all()
    serializer_class = ResetterSerializer
    lookup_field = 'resetter_uuid'

class ResetterUpdateAPIView(RetrieveUpdateAPIView):
    queryset = Resetter.objects.all()
    serializer_class = ResetterUpdateSerializer
    lookup_field = 'resetter_uuid'

class ResetterDeleteAPIView(DestroyAPIView):
    queryset = Resetter.objects.all()
    serializer_class = ResetterSerializer
    lookup_field = 'resetter_uuid'
