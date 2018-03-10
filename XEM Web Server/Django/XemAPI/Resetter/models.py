from django.db import models
from django.utils import timezone
import uuid

# Create your models here.
class Resetter(models.Model):
    resetter_uuid = models.UUIDField(default=uuid.uuid4, editable=False, unique=True)
    resetter_created_date = models.DateTimeField(default=timezone.now)
    user_email = models.CharField(max_length=50, null=True, default=None)
    location_uuid = models.CharField(max_length=50, null=True, default=None)
    class Meta:
        db_table = 'Resetter'
