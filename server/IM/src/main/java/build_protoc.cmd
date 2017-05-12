mkdir gen
cd gen
mkdir cpp
mkdir java
mkdir py
cd ..
start protoc -I=./probuf/ --java_out=./gen/java --cpp_out=./gen/cpp  --python_out=./gen/py  probuf/ 

