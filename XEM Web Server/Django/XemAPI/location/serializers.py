from rest_framework import serializers
from .models import Location

# Serializers define the API representation.
class LocationSerializer(serializers.ModelSerializer):
    class Meta:
        model = Location
        fields = ('__all__')

class LocationUpdateSerializer(serializers.ModelSerializer):
	class Meta:
		model = Location
		fields = ('location_name',
                'user_email')
