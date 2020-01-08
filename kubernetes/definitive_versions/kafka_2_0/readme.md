## README

These files were genereated by copying all the files, when doing:
```
kubectl apply -k github.com/Yolean/kubernetes-kafka/variants/dev-small/?ref=v6.0.3
```

Then you can get a resource from kubernetes:
```
kubectl get role --namespace=kafka -o yaml
```
And then direct this to a file, and remove timestamp etc.