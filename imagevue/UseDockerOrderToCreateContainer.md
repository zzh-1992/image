


// aliyun部署vue (需要将打包好的vue dist文件夹上传到服务器 nginx的挂载目录中，之后使用以下命令创建容器)

    docker run --name myFirstVueFront -d -p 80:80 \
    -v /usr/nginx/conf:/etc/nginx \
    -v /usr/nginx/dist:/usr/share/nginx/html \
    -v /usr/nginx/log:/var/log/nginx nginx


// mac部署vue

    docker run --name myFirstVueFront -d -p 80:80 \
    -v /Users/grape/DockerMount/nginx/conf:/etc/nginx \
    -v /Users/grape/IdeaProjects/image/imagevue/imagevue/dist:/usr/share/nginx/html \
    -v /Users/grape/DockerMount/nginx/log:/var/log/nginx nginx

