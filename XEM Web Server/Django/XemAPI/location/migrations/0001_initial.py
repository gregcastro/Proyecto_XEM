# Generated by Django 2.0.3 on 2018-03-08 20:23

from django.db import migrations, models
import django.utils.timezone
import uuid


class Migration(migrations.Migration):

    initial = True

    dependencies = [
    ]

    operations = [
        migrations.CreateModel(
            name='Location',
            fields=[
                ('id', models.AutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('location_uuid', models.UUIDField(default=uuid.uuid4, editable=False, unique=True)),
                ('location_created_date', models.DateTimeField(default=django.utils.timezone.now)),
                ('location_name', models.CharField(default=None, max_length=50, null=True)),
                ('user_email', models.CharField(default=None, max_length=50, null=True)),
            ],
            options={
                'db_table': 'Location',
            },
        ),
    ]