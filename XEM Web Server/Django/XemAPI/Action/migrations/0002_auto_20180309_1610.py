# Generated by Django 2.0.3 on 2018-03-09 20:10

from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('Action', '0001_initial'),
    ]

    operations = [
        migrations.AddField(
            model_name='action',
            name='status',
            field=models.BooleanField(default=1),
            preserve_default=False,
        ),
        migrations.AlterField(
            model_name='action',
            name='action_change_claymore_version',
            field=models.PositiveSmallIntegerField(default=None, null=True),
        ),
        migrations.AlterField(
            model_name='action',
            name='action_change_start_bat',
            field=models.PositiveSmallIntegerField(default=None, null=True),
        ),
        migrations.AlterField(
            model_name='action',
            name='action_download_claymore_version',
            field=models.PositiveSmallIntegerField(default=None, null=True),
        ),
        migrations.AlterField(
            model_name='action',
            name='action_reset_rig',
            field=models.PositiveSmallIntegerField(default=None, null=True),
        ),
        migrations.AlterField(
            model_name='action',
            name='action_restart_claymore',
            field=models.PositiveSmallIntegerField(default=None, null=True),
        ),
    ]
