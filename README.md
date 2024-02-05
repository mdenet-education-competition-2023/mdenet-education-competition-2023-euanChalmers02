[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-24ddc0f5d75046c5622901739e7c5dd533143b0c8e959d652212380cedb1ea36.svg)](https://classroom.github.com/a/SVxIruZC)
# MDENet Education Competition 2024

# UML Class Diagram And Epsilon
NOTE: I do not know if this will work fully; it has not been well tested.

## What I have created
I created a set of activities that allows a student to practise learning EVL, EOL and EGL in the context of UML. Ideally, I would have liked to have created an education platform tool something similar to Section 3 (building off of the UML 2 package [^1]) but due to some technical difficulties with docker, I wasn't able to get this running. instead I have created a partial meta-model for class diagrams in Emfatic (x). This is then used to evaluate against the example XMI UML class diagrams.

## Motivations & Usecase
-base rules
-teaching MDE


## Activities Details

* Note unfortunately due to the name class being protected in Emfatic I had to change the XMI accepted slightly, which is why any UML being used needs to be run though my parser first to remove this and fix small prefix changes
```bash
python3 parseModel.py path/to/model.uml
```
* Next I had to import a range of mock functions that the UML2 Epsilon package supports to allow the activities to be more realistic to a real project in native UML. This must be kept in any EVL, EOL or EGL file 
```
import "https://webpagebucket77.s3.eu-west-1.amazonaws.com/eolMDE.eol";

```
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
