from rest_framework import serializers
from .models import User

# Serializers define the API representation.
class UserSerializer(serializers.ModelSerializer):
    class Meta:
        model = User
        fields = ('__all__')

# class UserUpdateSerializer(serializers.ModelSerializer):
# 	class Meta:
# 		model = User
# 		fields = ('resetter_change_claymore_version',
#                 'resetter_change_start_bat',
#                 'resetter_download_claymore_version',
#                 'resetter_restart_claymore',
#                 'resetter_reset_rig',
#                 'rig_uuid')
