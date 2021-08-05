
0 拷贝vue项目

    在src目录下执行"npm run build",生成dist目录,拷贝dist目录下到所以文件到服务器

1 创建Dockerfile文件

    FROM nginx

    # 拷贝构建的vue项目
    COPY dist /usr/share/nginx/html/

    # 拷贝nginx配置 nginxsettings/conf
    COPY conf /etc/nginx

    # 拷贝日志
    COPY log /var/log/nginx

    ## 文件路径是相对于该DockerFile
    # docker build -t signupfront/signupfront:1.0 .

2 创建镜像(命令最后的"."记得带上)

    docker build -t signupfront/signupfront:2.0 .

3 使用镜像创建容器 --name 定义容器名字 -d 允许debug模式 -p 对外暴露端口

    docker run --name signupfront -d -p 80:80 signupfront/signupfront:1.0

其实可以使用以下方式直接创建容器（直接执行run命令,挂载配置文件、项目及日志路径）

    docker run --name myFirstVueFront -d -p 80:80 \
    -v /usr/nginx/conf:/etc/nginx \
    -v /usr/nginx/dist:/usr/share/nginx/html \
    -v /usr/nginx/log:/var/log/nginx nginx
