package com;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;



import twitter4j.FilterQuery;
import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;
import twitter4j.User;
import twitter4j.conf.ConfigurationBuilder;

public class Stream {
    public static void main(String[] args)  {
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true);
        
        /*
         access_token_key = "283132017-MlzIKlJHWpCKjPylkMIO3f4xlKeUXo2bnm6EZgEl"
access_token_secret = "lLDfQZ445j5LBuVPm81ZLNajUod5ROnu2CKkfOjU4vpVe"

consumer_key = "1hM0o74f73Hh4lyZVWYA"
consumer_secret = "4yvJ7j14ZNbzq50agG40NLtKspyrwegZC7C667Cx2Ck"
         * */
        
        cb.setOAuthConsumerKey("1hM0o74f73Hh4lyZVWYA");
        cb.setOAuthConsumerSecret("4yvJ7j14ZNbzq50agG40NLtKspyrwegZC7C667Cx2Ck");
        cb.setOAuthAccessToken("283132017-MlzIKlJHWpCKjPylkMIO3f4xlKeUXo2bnm6EZgEl");
        cb.setOAuthAccessTokenSecret("lLDfQZ445j5LBuVPm81ZLNajUod5ROnu2CKkfOjU4vpVe");

        TwitterStream twitterStream = new TwitterStreamFactory(cb.build()).getInstance();
        
        
       

        StatusListener listener = new StatusListener() {
        	
        	

            @Override
            public void onException(Exception arg0) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onDeletionNotice(StatusDeletionNotice arg0) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onScrubGeo(long arg0, long arg1) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onStatus(Status status) {
                User user = status.getUser();
                
                // gets Username
                String username = status.getUser().getScreenName();
                System.out.println(username);
                String profileLocation = user.getLocation();
                System.out.println(profileLocation);
                long tweetId = status.getId(); 
                System.out.println(tweetId);
                String content = status.getText();
                
                
                
                Stream obj = new Stream();
      		  HashMap<String, WordAttributes> cache2 = (HashMap<String, WordAttributes>)obj.run();
      		
          
      		  
      		  String newline;
			try {
				newline = obj.JSONAnalysis(content,cache2);
			//	newline = tweetId+"		"+username+"		"+profileLocation+"		"+newline+"		"+hashtags+"		"+retweet+	;
				newline = tweetId+"		"+username+"		"+profileLocation+"		"+newline ;
              //  System.out.println(newline);     
                
                
				 //////////
		        
		        File file = new File("D:/Processedblue22.txt");
		        
				// if file doesnt exists, then create it
				if (!file.exists()) {
					file.createNewFile();
				}

				FileWriter fw = new FileWriter(file.getAbsoluteFile());
				 BufferedWriter bw= new BufferedWriter(fw);
		     		
		        ////////////
         			   			
                bw.write(newline);
         			bw.newLine();
         			bw.close();
         		
				
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
             
                
                
                System.out.println(content +"\n");

            }

            @Override
            public void onTrackLimitationNotice(int arg0) {
                // TODO Auto-generated method stub

            }

			@Override
			public void onStallWarning(StallWarning arg0) {
				// TODO Auto-generated method stub
				
			}

        };
        FilterQuery fq = new FilterQuery();
    
        String keywords[] = {"Ibm,bluemix,impact,cloudfoundry,devops"};

        fq.track(keywords);

        twitterStream.addListener(listener);
        twitterStream.filter(fq);  

        
        /////////////////////////////////////////////////////////// ANALYSIS//////////////////////
       
		  
		  
    }
    
    
    
    
	  // method for loading the words from the csv file 
	 
	  public HashMap<String, WordAttributes> run() {
	 
		String csvFile = "E:/bluemix/all2.txt";
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";
		HashMap<String, WordAttributes> cache = new HashMap<String, WordAttributes>();
		try {
	 
			br = new BufferedReader(new FileReader(csvFile));
			while ((line = br.readLine()) != null) {
	 
			        // use comma as separator
				String[] word = line.split(cvsSplitBy);
				WordAttributes att =new WordAttributes();
				
				att.setDescription(word[0]);
				att.setWordNo(Double.parseDouble(word[1]));
				att.setValenceMean(Double.parseDouble(word[2]));
				att.setValenceSD(Double.parseDouble(word[3]));
				att.setArousalMean(Double.parseDouble(word[4]));
				att.setArousalSD(Double.parseDouble(word[5]));
				att.setDominanceMean(Double.parseDouble(word[6]));
				att.setDominanceSD(Double.parseDouble(word[7]));
				if(!word[8].endsWith(".")){
					att.setWordFrequency(Double.parseDouble(word[8]));
				}
				else if(word[8].endsWith(".")){
					att.setWordFrequency(0.0);
					
				}
				
				
				cache.put(word[0], att);
				
	 
				//System.out.println("word [code= " + word[0] 
	             //                    + " , Arousal SD=" + word[5] + "]");
	 
			}
	 
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	 
		System.out.println("Done");
		return cache;
	  }
	 
    
    
    //////////////////////////////////////////////////// Text Analysis 
	  String output;
	  public String JSONAnalysis(String tweet,  HashMap<String, WordAttributes> cache3) throws FileNotFoundException, IOException
      {
		
		  double WordNo,ValenceMean,ValenceSD,ArousalMean,ArousalSD,DominanceMean,DominanceSD,WordFrequency ;
	    	double Total_ValenceMean = 0 ,Total_ArousalMean=0;
	    	double Avg_ValenceMean = 0, Avg_ArousalMean=0;
	    	int MatchCount = 0;
	    	WordAttributes word = new WordAttributes();
		  
    	Iterator<String> keySetIterator = cache3.keySet().iterator();
    	
    	
		  while(keySetIterator.hasNext()){
			  
			
		 String word2 = keySetIterator.next();
		// System.out.println(word2) ;
		 
		  if(tweet.contains(word2)){
			  
			  MatchCount= MatchCount+1 ;
			  word = cache3.get(keySetIterator.next());
			  
			  ArousalMean =  word.getArousalMean() ;
			  ArousalSD =word.getArousalSD();
			  
			  ValenceMean =word.getValenceMean() ;
			  ValenceSD = word.getValenceSD();
			  
			  System.out.println("ValenceMean"+ValenceMean);
			  System.out.println(ValenceSD);
			  
			  
			  Total_ValenceMean = Total_ValenceMean+ ValenceMean;
			  System.out.println("Total_ValenceMean"+Total_ValenceMean);
			  Total_ArousalMean = Total_ArousalMean+  ArousalMean;
			  System.out.println("MatchCount"+MatchCount);
		  }
		  
		  
		  
		  
		  }
		  
		  Avg_ValenceMean =  (Total_ValenceMean / MatchCount) ;
		  
		  Avg_ArousalMean =  (Total_ArousalMean/MatchCount) ;
		  
		  
		  
    	
	//	  System.out.println((String)Avg_ArousalMean);
    	
    	
    	
    	
    	
    	output = tweet+"			"+Avg_ValenceMean+"			"+Avg_ArousalMean ;
    	
     
    	
		System.out.println(output);
    
    	
      	
    	
    	
    	return output;
    	
    
      }
  
    
    
    
    
    
    
    
    
}