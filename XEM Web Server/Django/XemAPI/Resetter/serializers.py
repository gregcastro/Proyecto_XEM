from rest_framework import serializers
from .models import Resetter

# Serializers define the API representation.
class ResetterSerializer(serializers.ModelSerializer):
    class Meta:
        model = Resetter
        fields = ('__all__')

class ResetterUpdateSerializer(serializers.ModelSerializer):
	class Meta:
		model = Resetter
		fields = ('resetter_change_claymore_version',
                'resetter_change_start_bat',
                'resetter_download_claymore_version',
                'resetter_restart_claymore',
                'resetter_reset_rig',
                'rig_uuid')
