FROM nginx

# 拷贝构建的vue项目
COPY imagevue/dist /usr/share/nginx/html/

# 拷贝nginx配置 nginxsettings/conf
COPY nginxsettings/conf /etc/nginx

# 拷贝日志
COPY nginxsettings/log /var/log/nginx

## 文件路径是相对于该DockerFile

