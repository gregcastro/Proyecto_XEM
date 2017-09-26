from flask import Flask

def app_conf():
	app = Flask(__name__)
	app.config['MYSQL_DATABASE_USER'] = 'root'
	app.config['MYSQL_DATABASE_PASSWORD'] = ''
	app.config['MYSQL_DATABASE_DB'] = 'xem'
	app.config['MYSQL_DATABASE_HOST'] = 'localhost'
	app.secret_key = "Estodeberiaserandom"

	app.config['TEMPLATES_AUTO_RELOAD'] = True	
	#Para enviar un correo
	# app.config['MAIL_SERVER']='smtp.gmail.com'
	# app.config['MAIL_PORT'] = 465
	# app.config['MAIL_USERNAME'] = 'escuelatecnicadonbosco@gmail.com'
	# app.config['MAIL_PASSWORD'] = 'administraciondonbosco'
	# app.config['MAIL_USE_TLS'] = False
	# app.config['MAIL_USE_SSL'] = True

	return app
