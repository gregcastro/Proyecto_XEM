from django.db import models
from django.utils import timezone
import uuid

# Create your models here.
class Location(models.Model):
    location_uuid = models.UUIDField(default=uuid.uuid4, editable=False, unique=True)
    location_created_date = models.DateTimeField(default=timezone.now)
    location_name = models.CharField(max_length=50, null=True, default=None)
    user_email = models.CharField(max_length=50, null=True, default=None)

    class Meta:
        db_table = 'Location'
