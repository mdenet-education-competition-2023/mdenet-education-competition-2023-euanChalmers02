## Learning Outcomes:
* Understand XMI format and how it relates to the renderings of a model
* Introduction to EVL (Epsilon Validation Language)
* Introducation to UML Model Heuristics

## Step 1:
Open the Platform (you should see the following page)
[add image here]

## Step 2 UML Models: 
We want you to learning about [XMI](https://en.wikipedia.org/wiki/XML_Metadata_Interchange) the standardised export format for many UML modelling tools. An example of this can be seen in the left hand window. 
When clicking the green run button next to this model this will run the Model Evaluation, where the platform will check the model against two aspects. 

The Problems panel will show a rendering of the model (dont worry if you dont recognise the format yet focus on the notes in light pink these will indicate any errors that are within the model.
Try to add another class to the XMI model which is named driving licience.

## Step 3 - Behind the Curtain:
The errors discussed above come from rules which are evaluted against the model. This is in the form [EVL](https://eclipse.dev/epsilon/doc/evl/). You should click on the EVL activity (left sidebar), 
This will now show a new panel that contains a couple of examples of these rules. Lets take a closer look at these rules.

[image of a rule]

**Context** is what will be evaluted against e.g. if content is class then the rule will be checked against every model element of class.
**Check**
**Message**
More info can be found [here](https://eclipse.dev/epsilon/doc/evl/)

Try to add a new rule that will check your newly added class above (driving licience) exists. HINT: are any of the existing rules similar.
Finally try to fix the XMI model to comply with all of the EVL rules. 

## Step 4: Modelling Heuristics:


## Further Reading & Activites
* Epsilon & MDE languages
* UML Feedback Tool ug4 project (uses many similar underlying techinlgies)
* Epsilon Book
