# Generated by Django 2.0.3 on 2018-03-19 15:09

from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('Rig', '0007_auto_20180319_1034'),
    ]

    operations = [
        migrations.AlterField(
            model_name='rig',
            name='rig_last_connection',
            field=models.CharField(default='1521486562.766665', max_length=50, null=True),
        ),
    ]