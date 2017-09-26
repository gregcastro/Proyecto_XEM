# encoding=utf-8
# -*- coding: utf-8 -*-
import config_vars
from flask import Flask, request, json, make_response, render_template, json, flash, redirect, url_for, session
from flaskext.mysql import MySQL

app = config_vars.app_conf()
mysql = MySQL()
mysql.init_app(app)

#===== Index y Login =====#
@app.route('/', methods=['GET','POST'])
def index():
	return render_template("index.html")


	# if(request.method == 'GET'): 
	# 	if(session.get('logged_in')):
	# 		if session.get('logged_in') == 'admin':
	# 			return render_template("index.html", admin=session.get('logged_in'))
	# 		return render_template("index.html", current_user=session.get('logged_in'))
	# 	return render_template("index.html")
	# else:
	# 	data = {"correo" : request.form['correo'], "clave" : request.form['password'] }
	# 	r = requests.post(url+'/login', json.dumps(data), headers=headers)
	# 	response = r.json()
		
	# 	if response['status'] == 200:

	# 		session['logged_in'] = response['access_token']
	# 		con = mysql.connect()
	# 		cursor = con.cursor()
	# 		cursor.execute("INSERT INTO auditoria (fecha, usuario, descripcion) VALUES(%s,%s,%s)", (date.today(), request.form['correo'], 'Nuevo inicio de sesión') )
	# 		con.commit()
	# 		return render_template("index.html", current_user=response['access_token'])

	# 	return render_template("index.html", error=response['error'])



@app.route('/rig', methods=['GET','POST'])
def rig():
	if(request.method == 'GET'): 
		con = mysql.connect()
		cursor = con.cursor()

		args = ()
		cursor.callproc('sp_rig_list', args)

		rig_list = cursor.fetchall()
		# rig_list = cursor.fetchone()

		data = json.dumps(rig_list)

		return '{}'.format(data)

	else:
		print('se crea el rig')




if __name__ == '__main__':
	app.run(debug=True)