library(rpart)
library(rpart.plot)
bike <- read.csv("capitalbikeshare-tripdata.csv")
bikeSample <- sample(115597,1000)
bikerTrain <- bike[bikeSample,c(1,4,6,9)]
bikerTest <- bike[-bikeSample,c(1,4,6,9)]

dim(bikerTrain)
dim(bikerTest)

summary(bikerTrain)
summary(bikerTest)
fit <- rpart(bikerTrain$Member.type~.,data=bikerTrain,method="class")
fit
rpart.plot(fit)
prediction <- predict(fit,newdata = bikerTest[,-4],type = ("class"))
table(bikerTest[,4],prediction)