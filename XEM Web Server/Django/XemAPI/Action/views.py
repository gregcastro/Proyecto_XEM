from django.shortcuts import render
from .models import Action
from .serializers import *
from rest_framework.response import Response
from rest_framework.permissions import AllowAny
from rest_framework.generics import (ListCreateAPIView,DestroyAPIView, RetrieveAPIView, RetrieveUpdateAPIView)


# Create your views here.

class ActionListCreateApiView(ListCreateAPIView):
    permission_classes = (AllowAny,)
    queryset = Action.objects.all()
    serializer_class = ActionSerializer

class ActionDetailApiView(RetrieveAPIView):
    queryset = Action.objects.all()
    serializer_class = ActionSerializer
    lookup_field = 'action_uuid'

class ActionUpdateAPIView(RetrieveUpdateAPIView):
    queryset = Action.objects.all()
    serializer_class = ActionUpdateSerializer
    lookup_field = 'action_uuid'

class ActionDeleteAPIView(DestroyAPIView):
    queryset = Action.objects.all()
    serializer_class = ActionSerializer
    lookup_field = 'action_uuid'
