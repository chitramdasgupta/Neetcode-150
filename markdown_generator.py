import os
import sys
import re

def format_heading(filename):
    # Remove .java extension and number prefix
    name_without_extension = filename.replace(".java", "")
    name_without_number = re.sub(r'^\d+_', '', name_without_extension)
    # Split by capital letters
    words = re.findall("[A-Z][^A-Z]*", name_without_number)
    return " ".join(words)

def process_file(file_path, output_file):
    filename = os.path.basename(file_path)
    heading = format_heading(filename)

    output_file.write(f"# {heading}\n\n")
    output_file.write("```java\n")

    with open(file_path, "r") as java_file:
        output_file.write(java_file.read())

    output_file.write("```\n\n")

def main(directory_path):
    output_file_path = os.path.join(directory_path, "main.md")

    try:
        with open(output_file_path, "w") as output_file:
            for filename in sorted(os.listdir(directory_path)):
                if filename.endswith(".java"):
                    file_path = os.path.join(directory_path, filename)
                    process_file(file_path, output_file)

        print("Markdown file 'main.md' has been created successfully.")
    except IOError as e:
        print(f"An error occurred: {e}")

if __name__ == "__main__":
    if len(sys.argv) != 2:
        print("Usage: python markdown_generator.py <directory_path>")
    else:
        directory_path = sys.argv[1]
        main(directory_path)

