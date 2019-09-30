library(e1071)
library(caTools)
diabetes <- read.table("diabetes.csv",header = TRUE, sep=",")
diabetes_split <- sample.split(diabetes,SplitRatio = 0.9)
diabetes_train <- subset(diabetes,diabetes_split==TRUE)
diabetes_test <- subset(diabetes,diabetes_split==FALSE)

categorize <- function(x) {
  if(x==1){
    result="Positive"
  }  
  else{
    result="Negative"
  } 
  return (result)   
}
diabetes$Outcome=sapply(diabetes$Outcome,categorize)
#golf_test
nb_default <- naiveBayes(Outcome~.,data=diabetes_train)
#nb_default
nb_predict <- predict(nb_default,diabetes_test,"raw")
hmm <- nb_predict
highestprob=colnames(nb_predict)[apply(hmm,1,which.max)]
table(highestprob,diabetes_test[,9])
