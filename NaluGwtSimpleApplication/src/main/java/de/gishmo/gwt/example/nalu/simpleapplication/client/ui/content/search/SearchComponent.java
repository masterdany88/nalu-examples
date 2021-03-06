/*
 * Copyright (c) 2018 - Frank Hossfeld
 *
 *  Licensed under the Apache License, Version 2.0 (the "License"); you may not
 *  use this file except in compliance with the License. You may obtain a copy of
 *  the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 *  WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 *  License for the specific language governing permissions and limitations under
 *  the License.
 *
 */

package de.gishmo.gwt.example.nalu.simpleapplication.client.ui.content.search;

import com.github.mvp4g.nalu.client.component.AbstractComponent;
import com.google.gwt.user.client.ui.*;
import de.gishmo.gwt.example.nalu.simpleapplication.client.resources.ApplicationConstants;
import de.gishmo.gwt.example.nalu.simpleapplication.client.resources.ApplicationCss;
import de.gishmo.gwt.example.nalu.simpleapplication.client.resources.ApplicationStyleFactory;
import de.gishmo.gwt.example.nalu.simpleapplication.client.widgets.TextField;

public class SearchComponent
    extends AbstractComponent<ISearchComponent.Controller, Widget>
    implements ISearchComponent {

  private TextField searchName;

  private TextField searchCity;

  private Button searchButton;

  private Button resetButton;

  public SearchComponent() {
  }

  @Override
  public void onAttach() {
    searchName.forceLayout();
    searchCity.forceLayout();
  }

  @Override
  public Widget render() {
    ApplicationCss style = ApplicationStyleFactory.get()
                                                  .getStyle();
    ScrollPanel panel = new ScrollPanel();

    FlowPanel searchPanel = new FlowPanel();
    searchPanel.addStyleName(style.searchPanel());
    panel.add(searchPanel);

    Label headline = new Label(ApplicationConstants.CONSTANTS.searchHeadline());
    headline.addStyleName(style.headline());
    searchPanel.add(headline);

    searchName = new TextField(ApplicationConstants.CONSTANTS.searchName());
    searchPanel.add(searchName);

    searchCity = new TextField(ApplicationConstants.CONSTANTS.searchCity());
    searchPanel.add(searchCity);

    FlowPanel buttonBar = new FlowPanel();
    buttonBar.addStyleName(style.searchPanelButtonBar());
    searchPanel.add(buttonBar);

    searchButton = new Button(ApplicationConstants.CONSTANTS.searchButton());
    searchButton.addStyleName(style.button());
    buttonBar.add(searchButton);

    resetButton = new Button(ApplicationConstants.CONSTANTS.resetButton());
    resetButton.addStyleName(style.button());
    buttonBar.add(resetButton);

    return panel;
  }

  @Override
  public void setSearchName(String searchName) {
    this.searchName.setText(searchName);
  }

  @Override
  public void setSearchCity(String searchCity) {
    this.searchCity.setText(searchCity);
  }

  @Override
  public void bind() {
    searchButton.addClickHandler(event -> getController().doClickSearchButton(searchName.getText(),
                                                                              searchCity.getText()));

    resetButton.addClickHandler(event -> {
      searchName.setText("");
      searchCity.setText("");
    });
  }
}
