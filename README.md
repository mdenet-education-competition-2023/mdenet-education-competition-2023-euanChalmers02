[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-24ddc0f5d75046c5622901739e7c5dd533143b0c8e959d652212380cedb1ea36.svg)](https://classroom.github.com/a/SVxIruZC)
# MDENet Education Competition 2024

# UML Class Diagram And Epsilon

## What I have created
I created a set of activities that allows a student to practise learning EVL, EOL and EGL in the context of UML. Ideally, I would have made an education platform tool similar to Section 3 (building off of the UML 2 package [^1]), but due to some technical difficulties with docker, I needed help to get this running. Instead, I have created a partial meta-model for class diagrams in Emfatic (x). This is then used to evaluate against the example XMI UML class diagrams.

## Motivations & Usecase
There are two main motivations for the creation of these activities:
* As part of my ug4 Honours Project at the University of Edinburgh, I have been developing a customisable and cloud-based UML class diagram evaluation system to provide detailed formative feedback to students, i felt that creating an activity for the education tool could be the perfect place for modelling experts to test EVL rules (the basis for my UML evaluations) before adding them to my tool.
* To help develop modellers understanding of possible uses for Epsilon and UML

## Activities Details

* Note: unfortunately, due to the name class being protected in Emfatic, I had to change the XMI accepted slightly, which is why any UML being used needs to be run through my parser first to remove this and fix small prefix changes.
```bash
python3 parseModel.py path/to/model.uml
```
* Next, I had to import a range of mock functions that the UML2 Epsilon package supports to make the activities more realistic to an actual project in native UML. This must be kept in any EVL, EOL or EGL file.
```
import "https://webpagebucket77.s3.eu-west-1.amazonaws.com/eolMDE.eol";
```

>>>
>>>Best way to run the activities:
>>>[Main activites]([https://www.openai.com](https://educationplatform.mde-network.org/?activities=https://webpagebucket77.s3.eu-west-1.amazonaws.com/mdeCompFinal/activity.json))
>>>

## Activities Overview / Worksheet

1. It is EOL-based and aims to introduce EOL and UML to the learner. (I assumed knowledge of EOL already, e.g., I completed a few Epsilon playground exercises).

2. EVL-based and introduces how to evaluate the provided model (This is based in the context of EVL, such as those used within my undergraduate dissertation, as I imagine the modeller could test base rules in the MDE education platform environment as it is more user-friendly)

3. EGL-based aims to guide the learner through another process of rendering the model into plantUML from the UML class diagram provided. (This can be tested by copying and pasting the generated code into plantUML online server) The same process is done within the Epsilon playground to create the Flexmi model renders.

4. Finally I have implemented the first of __ 5 OO metrics in EVL and encourage the learner to attempt to implement more as a final challenge[TRY HERE](https://educationplatform.mde-network.org/?activities=https://webpagebucket77.s3.eu-west-1.amazonaws.com/mdeCompFinal/activityHeuristics.json)

6. Finally, the learner can now explore the available task and attempt to modify the class diagram, e.g. trying to test and find problems with their EVL rules or add additional features to the EGL rendering (end multiplicities, label associations etc.)


## Possible Tool
Based on the tool template example. This should be replicated for EGL and EOL. Inspiration for this can be taken from Epsilon Playground [^2].
```Java
public class UMLTool  { 
	
	public UMLTool() { }

    public void runEVL(String inputEpsilon, OutputStream outputStream, JsonObject response) throws Exception {
	
		String result = "";

		/*-------------------------------------
		 *  Import the meta model 
		 *-------------------------------------*/
        EPackage.Registry.INSTANCE.put(UMLPackage.eNS_URI, UMLPackage.eINSTANCE);

        ResourceSet resourceSet = new ResourceSetImpl();
        resourceSet.getPackageRegistry().put(UMLPackage.eNS_URI, UMLPackage.eINSTANCE);
        resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("*", new UMLResourceFactoryImpl());
        Resource resource = resourceSet.createResource(URI.createURI(UMLPackage.eNS_URI));

        /*-------------------------------------
		 *  Load in the UML Model
		 *-------------------------------------*/
        resource.load(new ByteArrayInputStream(modelContents), null);
        InMemoryEmfModel modelMem = new InMemoryEmfModel(resource);
        modelMem.setName("Model");

		/*-------------------------------------
		 *  Load in the Epsilon Code
		 *-------------------------------------*/
        EvlModule module = new EvlModule();
        // takes in the evl code as string
        module.parse(evlCode);

		/*-------------------------------------
		 *  Tool Function 
		 *-------------------------------------*/	
        module.getContext().getModelRepository().addModel(modelMem);
        module.execute();


		result = result.concat(module.getContext().getUnsatisfiedConstraints().toString());  
		
		outputStream.write(result.getBytes());
	}

}

```

[^1]: https://github.com/creckord/org.eclipse.uml2)https://github.com/creckord/org.eclipse.um
[^2]: https://github.com/epsilonlabs/playground/blob/main/src/main/java/org/eclipse/epsilon/live/RunEpsilonFunction.java
