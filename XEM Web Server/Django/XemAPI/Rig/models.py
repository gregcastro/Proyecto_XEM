from django.db import models
from django.utils import timezone
import uuid
import datetime

# Create your models here.
class Rig(models.Model):
    rig_uuid = models.UUIDField(unique=True)
    rig_created_date = models.DateTimeField(default=timezone.now)
    rig_name = models.CharField(max_length=50, null=True, default=None)
    rig_email = models.CharField(max_length=50, null=True, default=None)
    rig_lan_ip = models.CharField(max_length=50, null=True, default=None)
    rig_claymore_version = models.CharField(max_length=4, null=True, default=None)
    rig_time_up = models.CharField(max_length=10, null=True, default=None)
    rig_reset_today = models.CharField(max_length=4, null=True, default=None)
    rig_claymore_reset_today = models.CharField(max_length=10, null=True, default=None)
    rig_gpu_info_eth = models.CharField(max_length=1000, null=True, default=None)
    rig_gpu_second_coin = models.CharField(max_length=10, null=True, default=None)
    rig_gpu_info_second_coin = models.CharField(max_length=1000, null=True, default=None)
    rig_start_bat_data = models.CharField(max_length=1000, null=True, default=None)
    location_uuid = models.CharField(max_length=50, null=True, default=None)
    rig_reseter_number = models.IntegerField(null=True, default=None)
    reset_uuid = models.CharField(max_length=1000, null=True, default=None)
    rig_last_connection = models.CharField(max_length=50, default=str( datetime.datetime.utcnow().timestamp()) )

    class Meta:
        db_table = 'Rig'
