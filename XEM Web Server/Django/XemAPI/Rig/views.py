from django.shortcuts import render
from .models import Rig
from .serializers import *
# from .validated_data import *
from rest_framework.response import Response
from django.utils import timezone
from rest_framework.generics import (ListCreateAPIView,DestroyAPIView, RetrieveAPIView, RetrieveUpdateAPIView)


# Create your views here.

# class RigListCreateApiView(ListCreateAPIView):
#     queryset = Rig.objects.all()
#     serializer_class = RigSerializer
#
#     def perform_create(self, serializer):
#         _rig = Rig.objects.filter(rig_uuid=serializer.validated_data['rig_uuid'], rig_lan_ip = serializer.validated_data['rig_lan_ip'])
#
#         if (_rig==None):
#             serializer.save()
#             print('Creado')
#         else:
#             queryset = _rig
#             serializer_class = RigUpdateSerializer
#             lookup_field = serializer.validated_data['rig_uuid']
#             serializer_class.save()

class RigListCreateApiView(ListCreateAPIView):
    queryset = Rig.objects.all()
    serializer_class = RigSerializer
    def perform_create(self, serializer):
        defaults = {
        'rig_uuid' : serializer.validated_data['rig_uuid'],
        'rig_created_date' : serializer.validated_data['rig_created_date'],
        'rig_name' : serializer.validated_data['rig_name'],
        'rig_email':serializer.validated_data['rig_email'],
		'rig_lan_ip':serializer.validated_data['rig_lan_ip'],
		'rig_claymore_version':serializer.validated_data['rig_claymore_version'],
		'rig_time_up':serializer.validated_data['rig_time_up'],
		'rig_reset_today':serializer.validated_data['rig_reset_today'],
		'rig_claymore_reset_today':serializer.validated_data['rig_claymore_reset_today'],
		'rig_gpu_info_eth':serializer.validated_data['rig_gpu_info_eth'],
		'rig_gpu_second_coin':serializer.validated_data['rig_gpu_second_coin'],
		'rig_gpu_info_second_coin':serializer.validated_data['rig_gpu_info_second_coin'],
		'rig_start_bat_data':serializer.validated_data['rig_start_bat_data'],
		'location_uuid':serializer.validated_data['location_uuid'],
		'rig_reseter_number':serializer.validated_data['rig_reseter_number'],
		'reset_uuid':serializer.validated_data['reset_uuid']}


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
