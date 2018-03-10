from rest_framework import serializers
from .models import Rig

# Serializers define the API representation.
class RigSerializer(serializers.ModelSerializer):
    class Meta:
        model = Rig
        fields = ('__all__')

class RigUpdateSerializer(serializers.ModelSerializer):
	class Meta:
		model = Rig
		fields = ('rig_name',
                'rig_email',
                'rig_lan_ip',
                'rig_claymore_version',
                'rig_time_up',
                'rig_reset_today',
                'rig_claymore_reset_today',
                'rig_gpu_info_eth','rig_gpu_second_coin',
                'rig_gpu_info_second_coin',
                'rig_start_bat_data',
                'location_uuid',
                'rig_reseter_number',
                'reset_uuid')
        #IMPORTANTE Falta colocar la Mac Address, eso lo vamos a cambiar, ya que en el minero va a haber una peticion
        # que utiliza la MacAddress desde el minero
