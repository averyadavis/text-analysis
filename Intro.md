# Introduction

## Background

Natural Language Processing (NLP) is an area of research at the intersection of linguistics and artificial intelligence.  Its overarching goal is to allow humans to interface with computers using naturally spoken or written languages.  The field was born nearly coincidently with arrival of widely available computing platforms for business in the early 1950s.  Until the 1990s the techniques for automated language recognition and understanding were limited to large sets of keywords and phrases along with a set of rules with which a program could attempt to derive useful meaning.  Advancements in the field came quickly with the advent of machine learning techniques replacing the hand written rules of the prior generation natural language systems.  Today, even greater strides have been made utilizing the throughly modern approach of representative learning used to train deep neural networks that can surface meaning and sentiment from natural languages with accuracies nearly indistinguishable from a human being.

## StanfordNLP

>The Stanford NLP Group makes some of our Natural Language Processing software available to everyone! We provide statistical NLP, deep learning NLP, and rule-based NLP tools for major computational linguistics problems, which can be incorporated into applications with human language technology needs. These packages are widely used in industry, academia, and government.

Source: [The Stanford NLP Group](https://nlp.stanford.edu/software/)

## API Overview

The StanfordNLP software api centers around three main abstractions:

* Document - the original text to be processed along with any meta-data attached by the annotators after processing.
* Annotator - a model that performs some transformation and/or analysis on a document updating it with relevant meta-data.
* Pipeline - a container that holds series of annotators that will be applied to a document submitted to the pipeline for processing.

The steps to apply any feature of the Stanford NLP library to a document are as follows:

1. Configure a Pipeline with the Annotators necessary to achieve set goals.
2. Create a Document containing the text to be processed.
3. Submit the Document to the Pipeline for processing.
4. Iterate over the processed Document retrieving the relevant meta-data for analysis.

Example:

```

	Properties properties = new Properties();
	properties.setProperty("annotators","tokenize, ssplit, pos, parse, sentiment");
			
	StanfordCoreNLP stanfordCoreNLP = new StanfordCoreNLP(properties);
	CoreDocument doc = new CoreDocument("Some text to be processed by the Stanford NLP library.");
	
	stanfordCoreNLP.annotate(doc);

```

References:
* [Wikipedia NLP](https://en.wikipedia.org/wiki/Natural_language_processing)
* [Stanford NLP Group](https://nlp.stanford.edu/)
* [CoreNLP](https://stanfordnlp.github.io/CoreNLP/)

Annotators of note:
  * [Parser](https://nlp.stanford.edu/software/lex-parser.html)
  * [Parts of Speech](https://nlp.stanford.edu/software/tagger.html)
  * [Sentiment Analysis](https://nlp.stanford.edu/sentiment/)
