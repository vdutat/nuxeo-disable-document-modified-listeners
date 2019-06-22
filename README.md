# nuxeo-disable-document-modified-listeners

This plugin disables all the **event listeners** registered on event `documentModified` when flag `disableAllDocumentModifiedListeners` with value `true` is set on the document's context before it is saved.

When **event listeners** gets disabled, an event **documentModifiedCanceled** is produced, it can be useful  for audit purpose.

# Requirements

Building requires the following software:

* git
* maven

# Build

```
git clone ...
cd nuxeo-custom-disable-document-modified-listeners

mvn clean install
```

# Installation

```
nuxeoctl mp-install nuxeo-disable-document-modified-listeners/nuxeo-disable-document-modified-listeners-package/target/nuxeo-disable-document-modified-listeners-package-*.zip
```

# Usage

Before saving the document, you can set flag `disableAllDocumentModifiedListeners` in the document's context in order to disable all the listeners registered on the `documentModified` event.

## Java

```
doc.setPropertyValue("myschema:myfield", "myvalue");
doc.putContextData("disableAllDocumentModifiedListeners", true);
session.saveDocument(doc);
```

## Automation scripting

```
  // Change document properties' value
  input['dc:description'] = 'New value';
  // Set context flag in order to disable all 'documentModified' listeners when event is processed
  input.doc.putContextData('disableAllDocumentModifiedListeners', true);
  Document.Save(input, {});
```

# Support

**These features are not part of the Nuxeo Production platform, they are not supported**

These solutions are provided for inspiration and we encourage customers to use them as code samples and learning resources.

This is a moving project (no API maintenance, no deprecation process, etc.) If any of these solutions are found to be useful for the Nuxeo Platform in general, they will be integrated directly into platform, not maintained here.


# Licensing

[Apache License, Version 2.0](http://www.apache.org/licenses/LICENSE-2.0)


# About Nuxeo

Nuxeo dramatically improves how content-based applications are built, managed and deployed, making customers more agile, innovative and successful. Nuxeo provides a next generation, enterprise ready platform for building traditional and cutting-edge content oriented applications. Combining a powerful application development environment with SaaS-based tools and a modular architecture, the Nuxeo Platform and Products provide clear business value to some of the most recognizable brands including Verizon, Electronic Arts, Sharp, FICO, the U.S. Navy, and Boeing. Nuxeo is headquartered in New York and Paris.

More information is available at [www.nuxeo.com](http://www.nuxeo.com).
