## Build project with Docker-compose

To start you must have docker and docker-compose installed on your system. Then you can simply run the command as is

```bash
docker-compose up -d
```

## Build application with Jenkinsfile for ansible

You must first setup Jenkins on a vm and also download ansible

## Build project with Kubernets

After you setup a k8s-vm, you have to install microk8s

```bash
snap install microk8s --classic
```

Then go to the backend directory  in terminal of the vm and run:
1. 
```bash
microk8s.kubectl apply -f k8s/postgres/postgres-pvc.yaml 
```

2. 
```bash
microk8s.kubectl apply -f k8s/postgres/postgres-deployment.yaml 
```

3. 
```bash
microk8s.kubectl apply -f k8s/postgres/postgres-svc.yaml 
```

4. 
```bash
microk8s.kubectl apply -f k8s/spring/spring-deployment.yaml 
```

5. 
```bash
microk8s.kubectl apply -f k8s/spring/spring-svc.yaml 
```

6. 
```bash
microk8s.kubectl apply -f k8s/vue/vue-deployment.yaml 
```

7. 
```bash
microk8s.kubectl apply -f k8s/vue/vue-svc.yaml 
```

8. 
```bash
microk8s.kubectl apply -f k8s/vue/vue-ingress.yaml 
```

You can type in a browser http://ylision.ddns.net/ ,to see the project

## Build project with Jenkins Docker

Download docker and after that execute the following commands on jenkins-vm

```bash
sudo groupadd docker
sudo usermod -aG docker jenkins 
sudo chmod 666 /var/run/docker.sock
```

## Build project with Jenkins Kubernets

You setup a k8s virtual machine and we connect it with the Jenkins user by creating a new k8s host.
You must have followed Jenkins with ansible and jenkins with docker steps first.
You create a pipeline project that clones to backend repository and executes k8s.Jenkinsfile.
