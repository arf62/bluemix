package com;
import java.io.Serializable;
public class WordAttributes implements Serializable {
	
	 private static final long serialVersionUID = 1L;
	
	
	private String Description ="";
	private double WordNo,ValenceMean,ValenceSD,ArousalMean,ArousalSD,DominanceMean,DominanceSD,WordFrequency ;
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	public double getWordNo() {
		return WordNo;
	}
	public void setWordNo(double wordNo) {
		WordNo = wordNo;
	}
	public double getValenceMean() {
		return ValenceMean;
	}
	public void setValenceMean(double valenceMean) {
		ValenceMean = valenceMean;
	}
	public double getValenceSD() {
		return ValenceSD;
	}
	public void setValenceSD(double valenceSD) {
		ValenceSD = valenceSD;
	}
	public double getArousalMean() {
		return ArousalMean;
	}
	public void setArousalMean(double arousalMean) {
		ArousalMean = arousalMean;
	}
	public double getArousalSD() {
		return ArousalSD;
	}
	public void setArousalSD(double arousalSD) {
		ArousalSD = arousalSD;
	}
	public double getDominanceMean() {
		return DominanceMean;
	}
	public void setDominanceMean(double dominanceMean) {
		DominanceMean = dominanceMean;
	}
	public double getDominanceSD() {
		return DominanceSD;
	}
	public void setDominanceSD(double dominanceSD) {
		DominanceSD = dominanceSD;
	}
	public double getWordFrequency() {
		return WordFrequency;
	}
	public void setWordFrequency(double wordFrequency) {
		WordFrequency = wordFrequency;
	}
	

}
