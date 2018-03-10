from django.db import models
from django.contrib.auth.models import AbstractUser
import uuid

# Create your models here.
class User(AbstractUser):
    wallet = models.CharField(max_length=500, null=True, default=None)
    start_bat_data = models.CharField(max_length=2000, null=True, default=None)
    uuid = models.UUIDField(default=uuid.uuid4, editable=False)
