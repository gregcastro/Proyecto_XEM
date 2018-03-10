from rest_framework import serializers
from .models import Action

# Serializers define the API representation.
class ActionSerializer(serializers.ModelSerializer):
    class Meta:
        model = Action
        fields = ('__all__')

class ActionUpdateSerializer(serializers.ModelSerializer):
	class Meta:
		model = Action
		fields = ('action_change_claymore_version',
                'action_change_start_bat',
                'action_download_claymore_version',
                'action_restart_claymore',
                'action_reset_rig',
                'rig_uuid')
