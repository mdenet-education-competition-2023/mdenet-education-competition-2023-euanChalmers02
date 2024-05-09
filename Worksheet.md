## Learning Outcomes:
* Understand the XMI format and how it relates to the renderings of a model
* Introduction to EVL (Epsilon Validation Language)
* Introduction to UML Model Heuristics

## Step 1 - Setup:
Open the Platform (you should see the following page)
[START HERE](link to the platform here)

[add image here]

## Step 2 - UML Models: 
We want you to learning about [XMI](https://en.wikipedia.org/wiki/XML_Metadata_Interchange) the standardised export format for many UML modelling tools. An example of this can be seen in the left hand window. 
When clicking the green run button next to this model, the Model Evaluation will run, where the platform will check the model against two aspects. 

The Problems panel will show a rendering of the model (don't worry if you don't recognise the format yet. Focus on the notes in light pink. These will indicate any errors within the model. Try to add another class to the XMI model named driving license.

## Step 3 - Behind the Curtain:
The errors discussed above come from rules that are evaluated against the model. This is in the form [EVL](https://eclipse.dev/epsilon/doc/evl/). You should click on the EVL activity (left sidebar). This will now show a new panel containing a couple of examples of these rules. Let's take a closer look at these rules.

<img width="980" alt="Screenshot 2024-05-08 at 13 15 27" src="https://github.com/mdenet-education-competition-2023/mdenet-education-competition-2023-euanChalmers02/assets/113519226/edd316b3-58d4-41d1-b2ae-2ce9e96cda4f">
EVL Rule Example

EVL rules are defined into code blocks, which contain a **guard** that will check if the rule should be executed, a **check** which defines the rule to be evaluated, and a **message** that returns a message if the rules fail. Each block can accept EOL(Epsilon Object Language), the parent language.

More info can be found [here](https://eclipse.dev/epsilon/doc/evl/)

Try to add a new rule that will check whether your newly added class above (driving licence) exists. HINT: Are any of the existing rules similar?
Finally, the XMI model must be fixed to comply with all EVL rules. 

## Step 4 - Modelling Heuristics:
UML class diagram model heuristics guide designing effective and modular software systems. Some key heuristics include [Chidamber & Kemerer object-oriented metrics](https://www.aivosto.com/project/help/pm-oo-ck.html) (Number of methods in a class, etc.) & snake case for method name, etc. Many domains may have additional requirements on modellers. Let's see how we can use the EVL rules above to evaluate a model against model heuristics.

Move to the bottom activity (left side panel); you should now see this page.

[add image]

You should take some time to read [Chidamber & Kemerer object-oriented metrics](https://www.aivosto.com/project/help/pm-oo-ck.html), select one additional metric, and attempt to write an EVL rule that evaluates for this. Ensure you have created multiple XMI test cases for models that comply and break each metric.


## Further Reading & Activities
* [Epsilon & MDE languages](https://eclipse.dev/epsilon/)
* [UML Feedback Tool ug4 project (uses many similar underlying technologies)](https://euanchalmers.s3.eu-west-1.amazonaws.com/FE/WelcomePage.html)
* [Epsilon Book](https://eclipse.dev/epsilon/doc/book/)