[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-24ddc0f5d75046c5622901739e7c5dd533143b0c8e959d652212380cedb1ea36.svg)](https://classroom.github.com/a/SVxIruZC)
# MDENet Education Competition 2024

# UML Class Diagram And Epsilon
NOTE: I do not know if this will work fully; it has not been well tested due to unknown subpaths for tools.

## What I have created
I created a set of activities that allows a student to practise learning EVL, EOL and EGL in the context of UML. Ideally, I would have made an education platform tool similar to Section 3 (building off of the UML 2 package [^1]) but due to some technical difficulties with docker, I couldn't get this running. Instead, I have created a partial meta-model for class diagrams in Emfatic (x). This is then used to evaluate against the example XMI UML class diagrams.

## Motivations & Usecase
-base rules
-teaching MDE


## Activities Details

* Note unfortunately, due to the name class being protected in Emfatic I had to change the XMI accepted slightly, which is why any UML being used needs to be run through my parser first to remove this and fix small prefix changes
```bash
python3 parseModel.py path/to/model.uml
```
* Next I had to import a range of mock functions that the UML2 Epsilon package supports to allow the activities to be more realistic to a real project in native UML. This must be kept in any EVL, EOL or EGL file 
```
import "https://webpagebucket77.s3.eu-west-1.amazonaws.com/eolMDE.eol";
```

## Activities Overview / Worksheet

1. It is EOL-based and aims to introduce EOL and UML to the learner. (I assumed knowledge of EOl already, e.g., I completed a few Epsilon playground exercises).


2. EVL-based and introduces how to evaluate the provided model (This is based in the context of EVL, such as those used within my undergraduate dissertation, as I imagine the modeller could test base rules in the MDE education platform environment as it is more user-friendly)



3. EGL-based aims to guide the learner through another process of rendering the model into plantUML from the UML class diagram provided. (This can be tested by copying and pasting the generated code into plantUML online server) Note that this is the same process as done within the epsilon playground to create the flexmi model renders.

4. Finally, the learner can now explore the available task and attempt to modify the class diagram e.g trying to test and find problems with there EVL rules or add additional features to the EGL rendering (end multiplicities,label associations etc)


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
