

1 从官网下载nodejs

    http://nodejs.cn/download/

// vue版本较低时报以下错误

    vue create is a Vue CLI 3 only command and you are using Vue CLI 2.9.6.
    You may want to run the following to upgrade to Vue CLI 3:

// 解决方式

    npm uninstall -g vue-cli
    npm install -g @vue/cli

2 查看vue版本

    vue --version

3 vue相关命令

    Commands:
    create [options] <app-name>                create a new project powered by vue-cli-service
    add [options] <plugin> [pluginOptions]     install a plugin and invoke its generator in an already created project
    invoke [options] <plugin> [pluginOptions]  invoke the generator of a plugin in an already created project
    inspect [options] [paths...]               inspect the webpack config in a project with vue-cli-service
    serve [options] [entry]                    serve a .js or .vue file in development mode with zero config
    build [options] [entry]                    build a .js or .vue file in production mode with zero config
    ui [options]                               start and open the vue-cli ui
    init [options] <template> <app-name>       generate a project from a remote template (legacy API, requires @vue/cli-init)
    config [options] [value]                   inspect and modify the config
    outdated [options]                         (experimental) check for outdated vue cli service / plugins
    upgrade [options] [plugin-name]            (experimental) upgrade vue cli service / plugins
    migrate [options] [plugin-name]            (experimental) run migrator for an already-installed cli plugin
    info                                       print debugging information about your environment

4 vue init webpack my-project

使用淘宝镜像

    npm install -g cnpm --registry=https://registry.npm.taobao.org
    安装Vue需要npm的版本大于3，所以我们先升级一下npm，输入命令
    cnpm install cnpm -g
    安装vue，输入命令
    cnpm install vue
    安装vue-cli，输入命令
    cnpm install --global vue-cli