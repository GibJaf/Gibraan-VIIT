library(data.table) # used for fread
library(plyr) # used for laply
library(stringr) # used for str_replace_all

system.time( data <- fread(file = "Tweets.csv" , header = TRUE , sep = "," ) )
if ( TRUE ) {
  
  neg <- scan("negative-words.txt", what="character", comment.char=";")
  pos <- scan("positive-words.txt", what="character", comment.char=";")
  
  colnames(data)[3] <- "tweet"  

  score.sentiment = function(tweets, pos.words, neg.words)
  {
    
    require(plyr)
    require(stringr)
    
    scores = laply(tweets, function(tweet, pos.words, neg.words) {
      
      
      
      tweet = gsub('https://','',tweet) # removes https://
      tweet = gsub('http://','',tweet) # removes http://
      tweet=gsub('[^[:graph:]]', ' ',tweet) ## removes graphic characters 
      #like emoticons 
      tweet = gsub('[[:punct:]]', '', tweet) # removes punctuation 
      tweet = gsub('[[:cntrl:]]', '', tweet) # removes control characters
      tweet = gsub('\\d+', '', tweet) # removes numbers
      tweet=str_replace_all(tweet,"[^[:graph:]]", " ") 
      
      tweet = tolower(tweet) # makes all letters lowercase
      
      word.list = str_split(tweet, '\\s+') # splits the tweets by word in a list
      
      words = unlist(word.list) # turns the list into vector
      
      pos.matches = match(words, pos.words) ## returns matching 
      #values for words from list 
      neg.matches = match(words, neg.words)
      
      pos.matches = !is.na(pos.matches) ## converts matching values to true of false
      neg.matches = !is.na(neg.matches)
      
      score = sum(pos.matches) - sum(neg.matches) # true and false are 
      #treated as 1 and 0 so they can be added
      
      return(score)
      
    }, pos.words, neg.words )
    
    scores.df = data.frame(score=scores, text=tweets)
    
    return(scores.df)
    
  }
  
  analysis = score.sentiment(data$tweet, pos, neg) # calls sentiment function
  
  png(filename = "Histogram.png")
  hist(analysis$score , main = "Histogram Of Sentiment Scores" , xlab = "Sentiment Values" , ylab = "Number Of Tweets")
  dev.off();
  
  analysis[ analysis$score > 0 , 1 ] = 1
  analysis[ analysis$score < 0 , 1 ] = -1
  
  table(analysis$score)
  
}
