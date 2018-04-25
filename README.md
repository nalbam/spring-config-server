# spring-config-server

## Docker
```
PORT=8888
PROFILE=default
USERNAME=username
PASSWORD=password
ENCRYPT_KEY=RandomString
```

## Openshift
### Creating a project
```
oc new-project sample --display-name="Sample" --description="Sample Project"
oc project sample
```

### Creating new apps
```
oc new-app -f https://raw.githubusercontent.com/nalbam/spring-config-server/master/openshift/templates/spring.json \
           -e PROFILE=dev -e USERNAME=username -e PASSWORD=password -e ENCRYPT_KEY=RandomString
```

### Creating new pipeline
```
oc create -f https://raw.githubusercontent.com/nalbam/spring-config-server/master/openshift/templates/pipeline.yaml
```
