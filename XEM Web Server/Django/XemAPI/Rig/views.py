from django.shortcuts import render
from .models import Rig
from .serializers import *
from rest_framework.response import Response
from django.utils import timezone
from rest_framework.generics import (ListCreateAPIView,DestroyAPIView, RetrieveAPIView, RetrieveUpdateAPIView)
import datetime


class RigListCreateApiView(ListCreateAPIView):
    queryset = Rig.objects.all()
    serializer_class = RigSerializer
    def perform_create(self, serializer):

        defaults = serializer.data
        defaults['rig_last_connection'] = datetime.datetime.utcnow().timestamp()

        try:
            #OJO, DEL RIG, EN EL APPBACKEND SE ENVIAN ALGUNOS DATOS EN NULL O SIMPLEMENTE NO SE ENVIAN
            #ENTONCES CREO QUE DEBEMOS USAR OTRO SERIALIZARZER,YA QUE CON ESTA ESTRUCTURA, VOY A BORRAR
            #DATOS COMO RESETTER UUID, LOCATION UUID, OJO SON DATOS QUE NO ENVIA EL RIG

            obj = Rig.objects.get(rig_uuid=serializer.validated_data['rig_uuid'], rig_lan_ip=serializer.validated_data['rig_lan_ip'])
            for key, value in defaults.items():
                setattr(obj, key, value)
            obj.save()
        except Rig.DoesNotExist:
            #Aca tengo que hacer el update del Rig, pero me tengo que asegurar de no afectar la fecha de creacion.
            # defaults.validated_data['rig_created_date'] = timezone.now()
            new_values = defaults
            new_values.update(defaults)
            obj = Rig(**new_values)
            obj.save()

class RigDetailApiView(RetrieveAPIView):
    queryset = Rig.objects.all()
    serializer_class = RigSerializer
    lookup_field = 'rig_uuid'

class RigUpdateAPIView(RetrieveUpdateAPIView):
    queryset = Rig.objects.all()
    serializer_class = RigUpdateSerializer
    lookup_field = 'rig_uuid'

class RigDeleteAPIView(DestroyAPIView):
    queryset = Rig.objects.all()
    serializer_class = RigSerializer
    lookup_field = 'rig_uuid'
