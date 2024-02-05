import sys

def process_xml(file_path):
    with open(file_path, 'r') as file:
        input_xml = file.read()

    input_xml = input_xml.replace("uml:", "")
    input_xml = input_xml.replace("Class", "ownedClass")
    input_xml = input_xml.replace("</xmi:XMI>", "")
    replc = """<xmi:XMI xmi:version="2.0" xmlns:xmi="http://www.omg.org/XMI" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:uml="http://www.eclipse.org/uml2/5.0.0/UML">"""
    input_xml = input_xml.replace(replc, "")
    input_xml = input_xml.replace("<?xml version=\"1.0\" encoding=\"ASCII\"?>", "<?nsuri xmi?>")

    if input_xml.endswith('</Model>'):
        print("The </Model> tag is at the end")
    else:
        input_xml = input_xml.replace("</Model>", "")
        input_xml = input_xml + "</Model>"

    non_empty_lines = [line for line in input_xml.splitlines() if line.strip()]
    result = '\n'.join(non_empty_lines)
    print(result)

if __name__ == "__main__":
    if len(sys.argv) != 2:
        print("Usage: python script_name.py file_path")
    else:
        file_path = sys.argv[1]
        process_xml(file_path)
