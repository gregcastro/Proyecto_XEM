from django.db import models
from django.utils import timezone
import uuid

# Create your models here.
class Action(models.Model):
    action_uuid = models.UUIDField(default=uuid.uuid4, editable=False, unique=True)
    action_created_date = models.DateTimeField(default=timezone.now)
    action_change_claymore_version = models.PositiveSmallIntegerField(null=True, default=None)
    action_change_start_bat = models.PositiveSmallIntegerField(null=True, default=None)
    action_download_claymore_version = models.PositiveSmallIntegerField(null=True, default=None)
    action_restart_claymore = models.PositiveSmallIntegerField(null=True, default=None)
    action_reset_rig = models.PositiveSmallIntegerField(null=True, default=None)
    rig_uuid = models.CharField(max_length=50, null=True, default=None)
    status = models.BooleanField()

    class Meta:
        db_table = 'Action'
