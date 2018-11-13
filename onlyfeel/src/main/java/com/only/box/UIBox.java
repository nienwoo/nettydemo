package com.only.box;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.util.HashMap;
import java.util.Map;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import com.only.component.ImageBorder;

/**
 * @author XiaHui
 * @date 2015年1月6日 上午11:35:09
 */
public class UIBox {

	private static final String KEY_WHITE_COLOR = "WhiteColor";
	private static final String KEY_EMPTY_COLOR = "EmptyColor";
	/******** image key *******/
	// button
	public static final String key_image_button_normal = "key_image_button_normal";
	public static final String key_image_button_disabled = "key_image_button_disabled";
	public static final String key_image_button_pressed = "key_image_button_pressed";
	public static final String key_image_button_rollover = "key_image_button_rollover";
	public static final String key_image_button_foucs = "key_image_button_foucs";
	// Separator
	public static final String key_image_separator_h = "key_image_separator_h";
	public static final String key_image_separator_v = "key_image_separator_v";
	// popup menu
	public static final String key_image_menu_shadow = "key_image_menu_shadow";
	public static final String key_image_menu_no_shadow = "key_image_menu_no_shadow";
	// menu
	public static final String key_image_menu_background = "key_image_menu_background";
	public static final String key_menu_item_background= "key_menu_item_background";
	// header panel
	public static final String key_image_common_header_default = "key_image_common_header_default";
	// scroll bar
	public static final String key_image_scroll_bar_down_normal = "key_image_scroll_bar_down_normal";
	public static final String key_image_scroll_bar_down_rollover = "key_image_scroll_bar_down_rollover";
	public static final String key_image_scroll_bar_down_pressed = "key_image_scroll_bar_down_pressed";
	public static final String key_image_scroll_bar_right_normal = "key_image_scroll_bar_right_normal";
	public static final String key_image_scroll_bar_right_rollover = "key_image_scroll_bar_right_rollover";
	public static final String key_image_scroll_bar_right_pressed = "key_image_scroll_bar_right_pressed";

	public static final String key_image_scroll_bar_up_normal = "key_image_scroll_bar_up_normal";
	public static final String key_image_scroll_bar_up_rollover = "key_image_scroll_bar_up_rollover";
	public static final String key_image_scroll_bar_up_pressed = "key_image_scroll_bar_up_pressed";
	public static final String key_image_scroll_bar_left_normal = "key_image_scroll_bar_left_normal";
	public static final String key_image_scroll_bar_left_rollover = "key_image_scroll_bar_left_rollover";
	public static final String key_image_scroll_bar_left_pressed = "key_image_scroll_bar_left_pressed";

	public static final String key_image_scroll_bar_v_pressed = "key_image_scroll_bar_v_pressed";
	public static final String key_image_scroll_bar_v_rollover = "key_image_scroll_bar_v_rollover";
	public static final String key_image_scroll_bar_v_normal = "key_image_scroll_bar_v_normal";

	public static final String key_image_scroll_bar_h_pressed = "key_image_scroll_bar_h_pressed";
	public static final String key_image_scroll_bar_h_rollover = "key_image_scroll_bar_h_rollover";
	public static final String key_image_scroll_bar_h_normal = "key_image_scroll_bar_h_normal";
	// toggle button
	public static final String key_image_toggle_button_normal = "key_image_toggle_button_normal";
	public static final String key_image_toggle_button_rollover = "key_image_toggle_button_rollover";
	public static final String key_image_toggle_button_disabled = "key_image_toggle_button_disabled";
	public static final String key_image_toggle_button_selected = "key_image_toggle_button_selected";
	public static final String key_image_toggle_button_selected_disabled = "key_image_toggle_button_selected_disabled";
	// combo_box
	public static final String key_image_combo_box_selected_item_background = "key_image_combo_box_selected_item_background";
	
	public static final String key_image_combobox_border_normal = "key_image_combobox_border_normal";
	public static final String key_image_combobox_border_rollover = "key_image_combobox_border_rollover";
	public static final String key_image_combobox_border_disabled = "key_image_combobox_border_disabled";
	
	
	//
	public static final String key_image_combo_box_renderer_border_inner = "key_image_combo_box_renderer_border_inner";
	public static final String key_image_combo_box_renderer_border_disabled = "key_image_combo_box_renderer_border_disabled";
	// combo_box_button
	public static final String key_image_combo_box_button_normal = "key_image_combo_box_button_normal";
	public static final String key_image_combo_box_button_rollover = "key_image_combo_box_button_rollover";
	public static final String key_image_combo_box_button_pressed = "key_image_combo_box_button_pressed";
	// spinner_previous
	public static final String key_image_spinner_previous_normal = "key_image_spinner_previous_normal";
	public static final String key_image_spinner_previous_pressed = "key_image_spinner_previous_pressed";
	public static final String key_image_spinner_previous_rollover = "key_image_spinner_previous_rollover";
	public static final String key_image_spinner_previous_disabled = "key_image_spinner_previous_disabled";
	// spinner_next
	public static final String key_image_spinner_next_normal = "key_image_spinner_next_normal";
	public static final String key_image_spinner_next_pressed = "key_image_spinner_next_pressed";
	public static final String key_image_spinner_next_rollover = "key_image_spinner_next_rollover";
	public static final String key_image_spinner_next_disabled = "key_image_spinner_next_disabled";
	// slider_background
	public static final String key_image_slider_background_h = "key_image_slider_background_h";
	public static final String key_image_slider_background_v = "key_image_slider_background_v";
	// List
	public static final String key_image_list_selected_item_normal_background = "key_image_list_selected_item_normal_background";
	public static final String key_image_list_selected_item_disabled_background = "key_image_list_selected_item_disabled_background";
	// tabbed_pane
	public static final String key_image_tabbed_pane_pressed = "key_image_tabbed_pane_pressed";
	public static final String key_image_tabbed_pane_rollover = "key_image_tabbed_pane_rollover";
	public static final String key_image_tabbed_pane_normal = "key_image_tabbed_pane_normal";
	//
	public static final String key_image_tabbed_pane_bacdground = "key_image_tabbed_pane_bacdground";
	//
	public static final String key_image_tabbed_pane_split_h = "key_image_tabbed_pane_split_h";
	public static final String key_image_tabbed_pane_split_v = "key_image_tabbed_pane_split_v";
	//
	public static final String key_image_tabbed_pane_previous_normal = "key_image_tabbed_pane_previous_normal";
	public static final String key_image_tabbed_pane_previous_disabled = "key_image_tabbed_pane_previous_disabled";
	public static final String key_image_tabbed_pane_previous_rollover = "key_image_tabbed_pane_previous_rollover";
	public static final String key_image_tabbed_pane_previous_pressed = "key_image_tabbed_pane_previous_pressed";

	public static final String key_image_tabbed_pane_next_normal = "key_image_tabbed_pane_next_normal";
	public static final String key_image_tabbed_pane_next_disabled = "key_image_tabbed_pane_next_disabled";
	public static final String key_image_tabbed_pane_next_rollover = "key_image_tabbed_pane_next_rollover";
	public static final String key_image_tabbed_pane_next_pressed = "key_image_tabbed_pane_next_pressed";

	// Table
	public static final String key_image_table_selected_item_normal_background = "key_image_table_selected_item_normal_background";
	public static final String key_image_table_selected_item_disabled_background = "key_image_table_selected_item_disabled_background";
	//
	public static final String key_image_table_header_default = "key_image_table_header_default";
	//
	public static final String key_image_table_header_pressed = "key_image_table_header_pressed";
	public static final String key_image_table_header_rollover = "key_image_table_header_rollover";
	public static final String key_image_table_header_split = "key_image_table_header_split";
	// check_box_list
	public static final String key_image_check_box_list_selected_item_normal_background = "key_image_check_box_list_selected_item_normal_background";
	public static final String key_image_check_box_list_selected_item_disabled_background = "key_image_check_box_list_selected_item_disabled_background";
	// scroll_table
	public static final String key_image_scroll_table_show_menu_button_normal = "key_image_scroll_table_show_menu_button_normal";
	public static final String key_image_scroll_table_show_menu_button_rollover = "key_image_scroll_table_show_menu_button_rollover";
	public static final String key_image_scroll_table_show_menu_button_pressed = "key_image_scroll_table_show_menu_button_pressed";
	// Tree
	public static final String key_image_tree_selected_item_normal_background = "key_image_tree_selected_item_normal_background";
	public static final String key_image_tree_selected_item_disabled_background = "key_image_tree_selected_item_disabled_background";
	// TreeTableCell
	public static final String key_image_tree_table_cell_selected_item_normal_background = "key_image_tree_table_cell_selected_item_normal_background";
	public static final String key_image_tree_table_cell_selected_item_disabled_background = "key_image_tree_table_cell_selected_item_disabled_background";
	// calendar_combo_box
	public static final String key_image_calendar_combo_box_button_normal = "key_image_calendar_combo_box_button_normal";
	public static final String key_image_calendar_combo_box_button_rollover = "key_image_calendar_combo_box_button_rollover";
	public static final String key_image_calendar_combo_box_button_pressed = "key_image_calendar_combo_box_button_pressed";
	//
	public static final String key_image_calendar_close_normal = "key_image_calendar_close_normal";
	public static final String key_image_calendar_close_rollover = "key_image_calendar_close_rollover";
	public static final String key_image_calendar_close_pressed = "key_image_calendar_close_pressed";
	public static final String key_image_calendar_next_month_normal = "key_image_calendar_next_month_normal";
	public static final String key_image_calendar_next_month_rollover = "key_image_calendar_next_month_rollover";
	public static final String key_image_calendar_next_month_pressed = "key_image_calendar_next_month_pressed";
	public static final String key_image_calendar_next_year_normal = "key_image_calendar_next_year_normal";
	public static final String key_image_calendar_next_year_rollover = "key_image_calendar_next_year_rollover";
	public static final String key_image_calendar_next_year_pressed = "key_image_calendar_next_year_pressed";
	public static final String key_image_calendar_previous_month_normal = "key_image_calendar_previous_month_normal";
	public static final String key_image_calendar_previous_month_rollover = "key_image_calendar_previous_month_rollover";
	public static final String key_image_calendar_previous_month_pressed = "key_image_calendar_previous_month_pressed";
	public static final String key_image_calendar_previous_year_normal = "key_image_calendar_previous_year_normal";
	public static final String key_image_calendar_previous_year_rollover = "key_image_calendar_previous_year_rollover";
	public static final String key_image_calendar_previous_year_pressed = "key_image_calendar_previous_year_pressed";
	//
	public static final String key_image_calendar_background = "key_image_calendar_background";

	// window
	public static final String key_image_window_title = "key_image_window_title";
	public static final String key_image_window_background = "key_image_window_background";
	
	
	
	public static final String key_image_window_close_normal= "key_image_window_close_normal";
	public static final String key_image_window_close_rollover= "key_image_window_close_rollover";
	public static final String key_image_window_close_pressed= "key_image_window_close_pressed";
	public static final String key_image_window_min_normal= "key_image_window_min_normal";
	public static final String key_image_window_min_rollover= "key_image_window_min_rollover";
	public static final String key_image_window_min_pressed= "key_image_window_min_pressed";
	public static final String key_image_window_restore_normal= "key_image_window_restore_normal";
	public static final String key_image_window_restore_rollover= "key_image_window_restore_rollover";
	public static final String key_image_window_restore_pressed= "key_image_window_restore_pressed";
	public static final String key_image_window_max_normal= "key_image_window_max_normal";
	public static final String key_image_window_max_rollover= "key_image_window_max_rollover";
	public static final String key_image_window_max_pressed= "key_image_window_max_pressed";
	
	
	/************ icon key ***************/

	// radio_button
	public static final String key_icon_radio_button_normal = "key_icon_radio_button_normal";
	public static final String key_icon_radio_button_rollover = "key_icon_radio_button_rollover";
	public static final String key_icon_radio_button_pressed = "key_icon_radio_button_pressed";
	public static final String key_icon_radio_button_disabled = "key_icon_radio_button_disabled";
	public static final String key_icon_radio_button_selected_normal = "key_icon_radio_button_selected_normal";
	public static final String key_icon_radio_button_selected_rollover = "key_icon_radio_button_Selected_rollover";
	public static final String key_icon_radio_button_selected_pressed = "key_icon_radio_button_Selected_pressed";
	public static final String key_icon_radio_button_selected_disabled = "key_icon_radio_button_selected_disabled";
	// CheckBox
	public static final String key_icon_checkbox_normal = "key_icon_checkbox_normal";
	public static final String key_icon_checkbox_pressed = "key_icon_checkbox_pressed";
	public static final String key_icon_checkbox_rollover = "key_icon_checkbox_rollover";
	public static final String key_icon_checkbox_disabled = "key_icon_checkbox_disabled";
	public static final String key_icon_checkbox_selected_normal = "key_icon_checkbox_selected_normal";
	public static final String key_icon_checkbox_selected_rollover = "key_icon_checkbox_selected_rollover";
	public static final String key_icon_checkbox_selected_pressed = "key_icon_checkbox_selected_pressed";
	public static final String key_icon_checkbox_selected_disabled = "key_icon_checkbox_selected_disabled";
	// tristate CheckBox
	public static final String key_icon_tristate_checkbox_notspecified_normal = "key_icon_tristate_checkbox_notspecified_normal";
	public static final String key_icon_tristate_checkbox_notspecified_rollover = "key_icon_tristate_checkbox_notspecified_rollover";
	public static final String key_icon_tristate_checkbox_notspecified_pressed = "key_icon_tristate_checkbox_notspecified_pressed";
	public static final String key_icon_tristate_checkbox_notspecified_disabled = "key_icon_tristate_checkbox_notspecified_disabled";
	// scroll_pane
	public static final String key_icon_scroll_pane_lower_right_corner_icon = "key_icon_scroll_pane_lower_right_corner_icon";
	// combo_box_button
	public static final String key_icon_combo_box_button_arrow_normal = "key_icon_combo_box_button_arrow_normal";
	public static final String key_icon_combo_box_button_arrow_disabled = "key_icon_combo_box_button_arrow_disabled";
	// menu
	public static final String key_icon_menu_arrow = "key_icon_menu_arrow";
	// menu_item
	public static final String key_icon_menu_radio_button_item_selected = "key_icon_menu_radio_button_item_selected";
	public static final String key_icon_menu_check_box_item_selected = "key_icon_menu_check_box_item_selected";
	// spinner_previous
	public static final String key_icon_spinner_previous_normal = "key_icon_spinner_previous_normal";
	public static final String key_icon_spinner_previous_disabled = "key_icon_spinner_previous_disabled";
	// /spinner_next
	public static final String key_icon_spinner_next_normal = "key_icon_spinner_next_normal";
	public static final String key_icon_spinner_next_disabled = "key_icon_spinner_next_disabled";
	// split_pane_touch
	public static final String key_icon_split_pane_touch_left = "key_icon_split_pane_touch_left";
	public static final String key_icon_split_pane_touch_up = "key_icon_split_pane_touch_up";
	public static final String key_icon_split_pane_touch_right = "key_icon_split_pane_touch_right";
	public static final String key_icon_split_pane_touch_down = "key_icon_split_pane_touch_down";
	// slider thumb
	public static final String key_icon_slider_thumb_h = "key_icon_slider_thumb_h";
	public static final String key_icon_slider_thumb_v = "key_icon_slider_thumb_v";
	public static final String key_icon_slider_min_thumb_h = "key_icon_slider_min_thumb_h";
	public static final String key_icon_slider_min_thumb_v = "key_icon_slider_min_thumb_v";
	// Table
	public static final String key_icon_table_header_up = "key_icon_table_header_up";
	public static final String key_icon_table_header_down = "key_icon_table_header_down";
	// Tree
	public static final String key_icon_tree_expanded = "key_icon_tree_expanded";
	public static final String key_icon_tree_collapsed = "key_icon_tree_collapsed";
	public static final String key_icon_tree_node_default = "key_icon_tree_node_default";
	// message_box
	public static final String key_icon_message_box_error = "key_icon_message_box_error";
	public static final String key_icon_message_box_information = "key_icon_message_box_information";
	public static final String key_icon_message_box_question = "key_icon_message_box_question";
	public static final String key_icon_message_box_warning = "key_icon_message_box_warning";

	/******** color key *******/
	// text fiel
	public static final String key_color_text_not_editable_background = "key_color_text_not_editable_background";
	public static final String key_color_text_disabled_background = "key_color_text_disabled_background";
	// password fiel
	public static final String key_color_password_not_editable_background = "key_color_password_not_editable_background";
	public static final String key_color_password_disabled_background = "key_color_password_disabled_background";

	// formatted fiel
	public static final String key_color_formatted_text_not_editable_background = "key_color_formatted_text_not_editable_background";
	public static final String key_color_formatted_text_disabled_background = "key_color_formatted_text_disabled_background";

	// scroll bar
	public static final String key_color_scroll_bar_border = "key_color_scroll_bar_border";
	public static final String key_color_scroll_bar_background = "key_color_scroll_bar_background";
	// combo_box_text
	public static final String key_color_combo_box_text_selection_foreground = "key_color_combo_box_text_selection_foreground";
	//
	public static final String key_color_combo_box_text_disabled_background = "key_color_combo_box_text_disabled_background";
	public static final String key_color_combo_box_text_not_editable_background = "key_color_combo_box_text_not_editable_background";
	// combo_box_editor
	public static final String key_color_combo_box_editor_text_selection = "key_color_combo_box_editor_text_selection";
	public static final String key_color_combo_box_editor_text_selection_foregroun = "key_color_combo_box_editor_text_selection_foregroun";
	// spinner_text
	public static final String key_color_spinner_text_selection_background = "key_color_spinner_text_selection_background";
	public static final String key_color_spinner_text_selection_foreground = "key_color_spinner_text_selection_foreground";
	//
	public static final String key_color_spinner_text_disabled_background = "key_color_spinner_text_disabled_background";
	// slider
	public static final String key_color_slider_disabled_tick = "key_color_slider_disabled_tick";
	// List
	public static final String key_color_list_disabled_background = "key_color_list_disabled_background";
	// Table
	public static final String key_color_table_text_disabled_background = "key_color_table_text_disabled_background";
	public static final String key_color_table_text_selection_foreground = "key_color_table_text_selection_foreground";
	public static final String key_color_table_text_selection = "key_color_table_text_selection";
	// scroll_list
	public static final String key_color_scroll_list_text_disabled_background = "key_color_scroll_list_text_disabled_background";

	// scroll_Table
	public static final String key_color_scroll_table_text_disabled_background = "key_color_scroll_table_text_disabled_background";
	// scroll_text
	public static final String key_color_scroll_text_not_editable_background = "key_color_scroll_text_not_editable_background";
	public static final String key_color_scroll_text_disabled_background = "key_color_scroll_text_disabled_background";
	// Tree
	public static final String key_color_tree_text_disabled_background = "key_color_tree_text_disabled_background";
	public static final String key_color_tree_line = "key_color_tree_line";
	public static final String key_color_tree_text_selection = "key_color_tree_text_selection";
	// Tree column_text_selection
	public static final String key_color_tree_column_text_selection = "key_color_tree_column_text_selection";
	public static final String key_color_tree_column_text_selection_foreground = "key_color_tree_column_text_selection_foreground";
	// calendar
	public static final String key_color_calendar_day_background = "key_color_calendar_day_background";
	//
	public static final String key_color_calendar_week_foreground = "key_color_calendar_week_foreground";
	public static final String key_color_calendar_weekend_foreground = "key_color_calendar_weekend_foreground";

	/******** border key *******/
	// text_field
	public static final String key_border_text_field_normal = "key_border_text_field_normal";
	public static final String key_border_text_field_rollover = "key_border_text_field_rollover";
	public static final String key_border_text_field_disabled = "key_border_text_field_disabled";
	public static final String key_border_text_field_not_editable = "key_border_text_field_not_editable";
	public static final String key_border_text_field_not_editable_rollover = "key_border_text_field_not_editable_rollover";
	// password_field
	public static final String key_border_password_field_normal = "key_border_password_field_normal";
	public static final String key_border_password_field_rollover = "key_border_password_field_rollover";
	public static final String key_border_password_field_disabled = "key_border_password_field_disabled";
	public static final String key_border_password_field_not_editable = "key_border_password_field_not_editable";
	public static final String key_border_password_field_not_editable_rollover = "key_border_password_field_not_editable_rollover";

	// formatted_text_field
	public static final String key_border_formatted_text_field_normal = "key_border_formatted_text_field_normal";
	public static final String key_border_formatted_text_field_rollover = "key_border_formatted_text_field_rollover";
	public static final String key_border_formatted_text_field_disabled = "key_border_formatted_text_field_disabled";
	public static final String key_border_formatted_text_field_not_editable = "key_border_formatted_text_field_not_editable";
	public static final String key_border_formatted_text_field_not_editable_rollover = "key_border_formatted_text_field_not_editable_rollover";

	// text_area
	public static final String key_border_text_area_normal = "key_border_text_area_normal";
	public static final String key_border_text_area_rollover = "key_border_text_area_rollover";
	public static final String key_border_text_area_disabled = "key_border_text_area_disabled";
	public static final String key_border_text_area_not_editable = "key_border_text_area_not_editable";
	public static final String key_border_text_area_not_editable_rollover = "key_border_text_area_not_editable_rollover";
	// combo_box_renderer

	public static final String key_border_combo_box_renderer_normal = "key_border_combo_box_renderer_normal";
	public static final String key_border_combo_box_renderer_selected = "key_border_combo_box_renderer_selected";
	//
	public static final String key_border_combo_box_popup = "key_border_combo_box_renderer_selected";
	// combo_box_editor
	public static final String key_border_combo_box_editor = "key_border_combo_box_editor";

	// spinner_editor
	public static final String key_border_spinner_editor_normal = "key_border_spinner_editor_normal";
	public static final String key_border_spinner_editor_disabled = "key_border_spinner_editor_disabled";

	// compound_text
	public static final String key_border_compound_text_normal = "key_border_compound_text_normal";
	public static final String key_border_compound_text_rollover = "key_border_compound_text_rollover";
	public static final String key_border_compound_text_disabled = "key_border_compound_text_disabled";
	// list_renderer
	public static final String key_border_list_renderer = "key_border_list_renderer";
	// Table
	public static final String key_border_table_editor = "key_border_table_editor";
	// checked_combo_box
	public static final String key_border_checked_combo_box_popup = "key_border_checked_combo_box_popup";
	// scroll_text
	public static final String key_border_scroll_text = "key_border_scroll_text";
	// scroll_text
	public static final String key_border_scroll_text_field_normal = "key_border_scroll_text_field_normal";
	public static final String key_border_scroll_text_field_rollover = "key_border_scroll_text_field_rollover";
	public static final String key_border_scroll_text_field_disabled = "key_border_scroll_text_field_disabled";
	public static final String key_border_scroll_text_field_not_editable = "key_border_scroll_text_field_not_editable";
	public static final String key_border_scroll_text_field_not_editable_rollover = "key_border_scroll_text_field_not_editable_rollover";
	// Tree
	public static final String key_border_tree_renderer = "key_border_tree_renderer";
	public static final String key_border_tree_editor = "key_border_tree_editor";
	// tree_column
	public static final String key_border_tree_column_editor = "key_border_tree_column_editor";
	// calendar
	public static final String key_border_calendar_day_normal = "key_border_calendar_day_normal";
	public static final String key_border_calendar_day_selected = "key_border_calendar_day_selected";

	/******** font key *******/
	public static final String key_button_font = "key_button_font";
	public static final String key_label_font = "key_label_font";

	private static final HashMap<String, Image> imageMap = new HashMap<String, Image>();
	private static final HashMap<String, ImageIcon> imageIconMap = new HashMap<String, ImageIcon>();
	private static final HashMap<String, Icon> iconMap = new HashMap<String, Icon>();
	private static final HashMap<String, Color> colorMap = new HashMap<String, Color>();
	private static final HashMap<String, Border> borderMap = new HashMap<String, Border>();
	private static final HashMap<String, Font> fontMap = new HashMap<String, Font>();

	static {
		/****** image *******/
		// button
		putImage(key_image_button_normal, OnlyImageBox.getClassPathImageByName("button/button_normal.png", true));
		putImage(key_image_button_disabled, OnlyImageBox.getClassPathImageByName("button/button_disabled.png", true));
		putImage(key_image_button_pressed, OnlyImageBox.getClassPathImageByName("button/button_pressed.png", true));
		putImage(key_image_button_rollover, OnlyImageBox.getClassPathImageByName("button/button_rollover.png", true));
		putImage(key_image_button_foucs, OnlyImageBox.getClassPathImageByName("button/button_foucs.png", true));

		// Separator
		putImage(key_image_separator_h, OnlyImageBox.getClassPathImageByName("separator/separator_h.png", true));
		putImage(key_image_separator_v, OnlyImageBox.getClassPathImageByName("separator/separator_v.png", true));

		// popup menu
		putImage(key_image_menu_shadow, OnlyImageBox.getClassPathImageByName("menu/menu_background.png", true));
		putImage(key_image_menu_shadow, OnlyImageBox.getClassPathImageByName("menu/menu_background_no_shadow.png", true));
		// menu_background
		putImage(key_image_menu_background, OnlyImageBox.getClassPathImageByName("menu/menu_background.png", true));
		putImage(key_menu_item_background, OnlyImageBox.getClassPathImageByName("menu/menu_item_background.png", true));
		
		// header panel
		putImage(key_image_common_header_default, OnlyImageBox.getClassPathImageByName("common/common_header_default.png", true));
		//

		putImage(key_image_scroll_bar_down_normal, OnlyImageBox.getClassPathImageByName("scrollbar/scroll_bar_down_normal.png", true));
		putImage(key_image_scroll_bar_down_rollover, OnlyImageBox.getClassPathImageByName("scrollbar/scroll_bar_down_rollover.png", true));
		putImage(key_image_scroll_bar_down_pressed, OnlyImageBox.getClassPathImageByName("scrollbar/scroll_bar_down_pressed.png", true));
		putImage(key_image_scroll_bar_right_normal, OnlyImageBox.getClassPathImageByName("scrollbar/scroll_bar_right_normal.png", true));
		putImage(key_image_scroll_bar_right_rollover, OnlyImageBox.getClassPathImageByName("scrollbar/scroll_bar_right_rollover.png", true));
		putImage(key_image_scroll_bar_right_pressed, OnlyImageBox.getClassPathImageByName("scrollbar/scroll_bar_right_pressed.png", true));

		putImage(key_image_scroll_bar_up_normal, OnlyImageBox.getClassPathImageByName("scrollbar/scroll_bar_up_normal.png", true));
		putImage(key_image_scroll_bar_up_rollover, OnlyImageBox.getClassPathImageByName("scrollbar/scroll_bar_up_rollover.png", true));
		putImage(key_image_scroll_bar_up_pressed, OnlyImageBox.getClassPathImageByName("scrollbar/scroll_bar_up_pressed.png", true));
		putImage(key_image_scroll_bar_left_normal, OnlyImageBox.getClassPathImageByName("scrollbar/scroll_bar_left_normal.png", true));
		putImage(key_image_scroll_bar_left_rollover, OnlyImageBox.getClassPathImageByName("scrollbar/scroll_bar_left_rollover.png", true));
		putImage(key_image_scroll_bar_left_pressed, OnlyImageBox.getClassPathImageByName("scrollbar/scroll_bar_left_pressed.png", true));

		putImage(key_image_scroll_bar_v_pressed, OnlyImageBox.getClassPathImageByName("scrollbar/scroll_bar_v_pressed.png", true));
		putImage(key_image_scroll_bar_v_rollover, OnlyImageBox.getClassPathImageByName("scrollbar/scroll_bar_v_rollover.png", true));
		putImage(key_image_scroll_bar_v_normal, OnlyImageBox.getClassPathImageByName("scrollbar/scroll_bar_v_normal.png", true));

		putImage(key_image_scroll_bar_h_pressed, OnlyImageBox.getClassPathImageByName("scrollbar/scroll_bar_h_pressed.png", true));
		putImage(key_image_scroll_bar_h_rollover, OnlyImageBox.getClassPathImageByName("scrollbar/scroll_bar_h_rollover.png", true));
		putImage(key_image_scroll_bar_h_normal, OnlyImageBox.getClassPathImageByName("scrollbar/scroll_bar_h_normal.png", true));
		// toggle_button
		putImage(key_image_toggle_button_normal, OnlyImageBox.getClassPathImageByName("button/toggle_button_normal.png", true));
		putImage(key_image_toggle_button_rollover, OnlyImageBox.getClassPathImageByName("button/toggle_button_rollover.png", true));
		putImage(key_image_toggle_button_disabled, OnlyImageBox.getClassPathImageByName("button/toggle_button_disabled.png", true));
		putImage(key_image_toggle_button_selected, OnlyImageBox.getClassPathImageByName("button/toggle_button_selected.png", true));
		putImage(key_image_toggle_button_selected_disabled, OnlyImageBox.getClassPathImageByName("button/toggle_button_selected_disabled.png", true));
		// combo_box
		putImage(key_image_combo_box_selected_item_background, OnlyImageBox.getClassPathImageByName("combobox/combo_box_selected_item_background.png", true));
		
		putImage(key_image_combobox_border_normal, OnlyImageBox.getClassPathImageByName("combobox/combobox_border_normal.png", true));
		putImage(key_image_combobox_border_rollover, OnlyImageBox.getClassPathImageByName("combobox/combobox_border_rollover.png", true));
		putImage(key_image_combobox_border_disabled, OnlyImageBox.getClassPathImageByName("combobox/combobox_border_disabled.png", true));

		//
		putImage(key_image_combo_box_renderer_border_inner, OnlyImageBox.getClassPathImageByName("combobox/combo_box_renderer_border_inner.png", true));
		putImage(key_image_combo_box_renderer_border_disabled, OnlyImageBox.getClassPathImageByName("combobox/combo_box_renderer_border_disabled.png", true));
		//
		putImage(key_image_combo_box_button_normal, OnlyImageBox.getClassPathImageByName("combobox/combo_box_button_normal.png", true));
		putImage(key_image_combo_box_button_rollover, OnlyImageBox.getClassPathImageByName("combobox/combo_box_button_rollover.png", true));
		putImage(key_image_combo_box_button_pressed, OnlyImageBox.getClassPathImageByName("combobox/combo_box_button_pressed.png", true));
		// spinner_previous
		putImage(key_image_spinner_previous_normal, OnlyImageBox.getClassPathImageByName("spinner/spinner_previous_button_normal.png", true));
		putImage(key_image_spinner_previous_pressed, OnlyImageBox.getClassPathImageByName("spinner/spinner_previous_button_pressed.png", true));
		putImage(key_image_spinner_previous_rollover, OnlyImageBox.getClassPathImageByName("spinner/spinner_previous_button_rollover.png", true));
		putImage(key_image_spinner_previous_disabled, OnlyImageBox.getClassPathImageByName("spinner/spinner_previous_button_disabled.png", true));
		// spinner_next
		putImage(key_image_spinner_next_normal, OnlyImageBox.getClassPathImageByName("spinner/spinner_next_button_normal.png", true));
		putImage(key_image_spinner_next_pressed, OnlyImageBox.getClassPathImageByName("spinner/spinner_next_button_pressed.png", true));
		putImage(key_image_spinner_next_rollover, OnlyImageBox.getClassPathImageByName("spinner/spinner_next_button_rollover.png", true));
		putImage(key_image_spinner_next_disabled, OnlyImageBox.getClassPathImageByName("spinner/spinner_next_button_disabled.png", true));
		// slider_background
		putImage(key_image_slider_background_h, OnlyImageBox.getClassPathImageByName("slider/slider_background_h.png", true));
		putImage(key_image_slider_background_v, OnlyImageBox.getClassPathImageByName("slider/slider_background_v.png", true));
		// List
		putImage(key_image_list_selected_item_normal_background, OnlyImageBox.getClassPathImageByName("list/list_selected_item_normal_background.png", true));
		putImage(key_image_list_selected_item_disabled_background, OnlyImageBox.getClassPathImageByName("list/list_selected_item_disabled_background.png", true));
		// tabbed_pane
		putImage(key_image_tabbed_pane_pressed, OnlyImageBox.getClassPathImageByName("tab/tabbed_pane_tab_pressed.png", true));
		putImage(key_image_tabbed_pane_rollover, OnlyImageBox.getClassPathImageByName("tab/tabbed_pane_tab_rollover.png", true));
		putImage(key_image_tabbed_pane_normal, OnlyImageBox.getClassPathImageByName("tab/tabbed_pane_tab_normal.png", true));
	
		
		//
		putImage(key_image_tabbed_pane_bacdground, OnlyImageBox.getClassPathImageByName("tab/tabbed_pane_bacdground.png", true));
		//
		putImage(key_image_tabbed_pane_split_h, OnlyImageBox.getClassPathImageByName("tab/tabbed_pane_split_h.png", true));
		putImage(key_image_tabbed_pane_split_v, OnlyImageBox.getClassPathImageByName("tab/tabbed_pane_split_v.png", true));
		//
		putImage(key_image_tabbed_pane_previous_normal, OnlyImageBox.getClassPathImageByName("tab/tabbed_pane_previous_normal.png", true));
		putImage(key_image_tabbed_pane_previous_disabled, OnlyImageBox.getClassPathImageByName("tab/tabbed_pane_previous_disabled.png", true));
		putImage(key_image_tabbed_pane_previous_rollover, OnlyImageBox.getClassPathImageByName("tab/tabbed_pane_previous_rollover.png", true));
		putImage(key_image_tabbed_pane_previous_pressed, OnlyImageBox.getClassPathImageByName("tab/tabbed_pane_previous_pressed.png", true));

		//
		putImage(key_image_tabbed_pane_next_normal, OnlyImageBox.getClassPathImageByName("tab/tabbed_pane_next_normal.png", true));
		putImage(key_image_tabbed_pane_next_disabled, OnlyImageBox.getClassPathImageByName("tab/tabbed_pane_next_disabled.png", true));
		putImage(key_image_tabbed_pane_next_rollover, OnlyImageBox.getClassPathImageByName("tab/tabbed_pane_next_rollover.png", true));
		putImage(key_image_tabbed_pane_next_pressed, OnlyImageBox.getClassPathImageByName("tab/tabbed_pane_next_pressed.png", true));
		// Table
		putImage(key_image_table_selected_item_normal_background, OnlyImageBox.getClassPathImageByName("table/table_selected_item_normal_background.png", true));
		putImage(key_image_table_selected_item_disabled_background, OnlyImageBox.getClassPathImageByName("table/table_selected_item_disabled_background.png", true));
		//
		putImage(key_image_table_header_default, OnlyImageBox.getClassPathImageByName("table/table_header_default.png", true));
		//
		putImage(key_image_table_header_pressed, OnlyImageBox.getClassPathImageByName("table/table_header_pressed.png", true));
		putImage(key_image_table_header_rollover, OnlyImageBox.getClassPathImageByName("table/table_header_rollover.png", true));
		putImage(key_image_table_header_split, OnlyImageBox.getClassPathImageByName("table/table_header_split.png", true));
		// check_box_list
		putImage(key_image_check_box_list_selected_item_normal_background, OnlyImageBox.getClassPathImageByName("list/check_box_list_selected_item_normal_background.png", true));
		putImage(key_image_check_box_list_selected_item_disabled_background, OnlyImageBox.getClassPathImageByName("list/check_box_list_selected_item_disabled_background.png", true));
		// scroll_table
		putImage(key_image_scroll_table_show_menu_button_normal, OnlyImageBox.getClassPathImageByName("scrollbar/scroll_table_show_menu_button_normal.png", true));
		putImage(key_image_scroll_table_show_menu_button_rollover, OnlyImageBox.getClassPathImageByName("scrollbar/scroll_table_show_menu_button_rollover.png", true));
		putImage(key_image_scroll_table_show_menu_button_pressed, OnlyImageBox.getClassPathImageByName("scrollbar/scroll_table_show_menu_button_pressed.png", true));
		// Tree
		putImage(key_image_tree_selected_item_normal_background, OnlyImageBox.getClassPathImageByName("tree/tree_selected_item_normal_background.png", true));
		putImage(key_image_tree_selected_item_disabled_background, OnlyImageBox.getClassPathImageByName("tree/tree_selected_item_disabled_background.png", true));
		// tree_table_cell
		putImage(key_image_tree_table_cell_selected_item_normal_background, OnlyImageBox.getClassPathImageByName("tree/tree_table_cell_selected_item_normal_background.png", true));
		putImage(key_image_tree_table_cell_selected_item_disabled_background, OnlyImageBox.getClassPathImageByName("tree/tree_table_cell_selected_item_disabled_background.png", true));
		// calendar_combo_box
		putImage(key_image_calendar_combo_box_button_normal, OnlyImageBox.getClassPathImageByName("calendar/calendar_button_normal.png", true));
		putImage(key_image_calendar_combo_box_button_rollover, OnlyImageBox.getClassPathImageByName("calendar/calendar_button_rollover.png", true));
		putImage(key_image_calendar_combo_box_button_pressed, OnlyImageBox.getClassPathImageByName("calendar/calendar_button_pressed.png", true));
		//
		putImage(key_image_calendar_close_normal, OnlyImageBox.getClassPathImageByName("calendar/calendar_close_normal.png", true));
		putImage(key_image_calendar_close_rollover, OnlyImageBox.getClassPathImageByName("calendar/calendar_close_rollover.png", true));
		putImage(key_image_calendar_close_pressed, OnlyImageBox.getClassPathImageByName("calendar/calendar_close_pressed.png", true));
		putImage(key_image_calendar_next_month_normal, OnlyImageBox.getClassPathImageByName("calendar/calendar_next_month_normal.png", true));
		putImage(key_image_calendar_next_month_rollover, OnlyImageBox.getClassPathImageByName("calendar/calendar_next_month_rollover.png", true));
		putImage(key_image_calendar_next_month_pressed, OnlyImageBox.getClassPathImageByName("calendar/calendar_next_month_pressed.png", true));
		putImage(key_image_calendar_next_year_normal, OnlyImageBox.getClassPathImageByName("calendar/calendar_next_year_normal.png", true));
		putImage(key_image_calendar_next_year_rollover, OnlyImageBox.getClassPathImageByName("calendar/calendar_next_year_rollover.png", true));
		putImage(key_image_calendar_next_year_pressed, OnlyImageBox.getClassPathImageByName("calendar/calendar_next_year_pressed.png", true));
		putImage(key_image_calendar_previous_month_normal, OnlyImageBox.getClassPathImageByName("calendar/calendar_previous_month_normal.png", true));
		putImage(key_image_calendar_previous_month_rollover, OnlyImageBox.getClassPathImageByName("calendar/calendar_previous_month_rollover.png", true));
		putImage(key_image_calendar_previous_month_pressed, OnlyImageBox.getClassPathImageByName("calendar/calendar_previous_month_pressed.png", true));
		putImage(key_image_calendar_previous_year_normal, OnlyImageBox.getClassPathImageByName("calendar/calendar_previous_year_normal.png", true));
		putImage(key_image_calendar_previous_year_rollover, OnlyImageBox.getClassPathImageByName("calendar/calendar_previous_year_rollover.png", true));
		putImage(key_image_calendar_previous_year_pressed, OnlyImageBox.getClassPathImageByName("calendar/calendar_previous_year_pressed.png", true));
		//
		putImage(key_image_calendar_background, OnlyImageBox.getClassPathImageByName("calendar/calendar_background.png", true));
		// window
		putImage(key_image_window_title, OnlyImageBox.getClassPathImageByName("window/window_title.png"));
		putImage(key_image_window_background, OnlyImageBox.getClassPathImageByName("window/window_background.png"));
		
		
		putImage(key_image_window_close_normal, OnlyImageBox.getClassPathImageByName("window/window_close_normal.png"));
		putImage(key_image_window_close_rollover, OnlyImageBox.getClassPathImageByName("window/window_close_rollover.png"));
		putImage(key_image_window_close_pressed, OnlyImageBox.getClassPathImageByName("window/window_close_pressed.png"));
		putImage(key_image_window_min_normal, OnlyImageBox.getClassPathImageByName("window/window_min_normal.png"));
		putImage(key_image_window_min_rollover, OnlyImageBox.getClassPathImageByName("window/window_min_rollover.png"));
		putImage(key_image_window_min_pressed, OnlyImageBox.getClassPathImageByName("window/window_min_pressed.png"));
		putImage(key_image_window_restore_normal, OnlyImageBox.getClassPathImageByName("window/window_restore_normal.png"));
		putImage(key_image_window_restore_rollover, OnlyImageBox.getClassPathImageByName("window/window_restore_rollover.png"));
		putImage(key_image_window_restore_pressed, OnlyImageBox.getClassPathImageByName("window/window_restore_pressed.png"));
		putImage(key_image_window_max_normal, OnlyImageBox.getClassPathImageByName("window/window_max_normal.png"));
		putImage(key_image_window_max_rollover, OnlyImageBox.getClassPathImageByName("window/window_max_rollover.png"));
		putImage(key_image_window_max_pressed, OnlyImageBox.getClassPathImageByName("window/window_max_pressed.png"));
		
		
		
		/****** icon *******/

		// radio_button
		putIcon(key_icon_radio_button_normal, OnlyImageBox.getClassPathIconByName("radio/radio_button_normal.png", true));
		putIcon(key_icon_radio_button_rollover, OnlyImageBox.getClassPathIconByName("radio/radio_button_rollover.png", true));
		putIcon(key_icon_radio_button_pressed, OnlyImageBox.getClassPathIconByName("radio/radio_button_pressed.png", true));
		putIcon(key_icon_radio_button_disabled, OnlyImageBox.getClassPathIconByName("radio/radio_button_disabled.png", true));
		putIcon(key_icon_radio_button_selected_normal, OnlyImageBox.getClassPathIconByName("radio/radio_button_selected_normal.png", true));
		putIcon(key_icon_radio_button_selected_rollover, OnlyImageBox.getClassPathIconByName("radio/radio_button_selected_rollover.png", true));
		putIcon(key_icon_radio_button_selected_pressed, OnlyImageBox.getClassPathIconByName("radio/radio_button_selected_pressed.png", true));
		putIcon(key_icon_radio_button_selected_disabled, OnlyImageBox.getClassPathIconByName("radio/radio_button_selected_disabled.png", true));
		// checkbox
		putIcon(key_icon_checkbox_normal, OnlyImageBox.getClassPathIconByName("checkbox/checkbox_normal.png", true));
		putIcon(key_icon_checkbox_pressed, OnlyImageBox.getClassPathIconByName("checkbox/checkbox_pressed.png", true));
		putIcon(key_icon_checkbox_rollover, OnlyImageBox.getClassPathIconByName("checkbox/checkbox_rollover.png", true));
		putIcon(key_icon_checkbox_disabled, OnlyImageBox.getClassPathIconByName("checkbox/checkbox_disabled.png", true));
		putIcon(key_icon_checkbox_selected_normal, OnlyImageBox.getClassPathIconByName("checkbox/checkbox_selected_normal.png", true));
		putIcon(key_icon_checkbox_selected_rollover, OnlyImageBox.getClassPathIconByName("checkbox/checkbox_selected_rollover.png", true));
		putIcon(key_icon_checkbox_selected_pressed, OnlyImageBox.getClassPathIconByName("checkbox/checkbox_selected_pressed.png", true));
		putIcon(key_icon_checkbox_selected_disabled, OnlyImageBox.getClassPathIconByName("checkbox/checkbox_selected_disabled.png", true));
		// tristate_checkbox
		putIcon(key_icon_tristate_checkbox_notspecified_normal, OnlyImageBox.getClassPathIconByName("tree/tristate_checkbox_notspecified_normal.png", true));
		putIcon(key_icon_tristate_checkbox_notspecified_rollover, OnlyImageBox.getClassPathIconByName("tree/tristate_checkbox_notspecified_rollover.png", true));
		putIcon(key_icon_tristate_checkbox_notspecified_pressed, OnlyImageBox.getClassPathIconByName("tree/tristate_checkbox_notspecified_pressed.png", true));
		putIcon(key_icon_tristate_checkbox_notspecified_disabled, OnlyImageBox.getClassPathIconByName("tree/tristate_checkbox_notspecified_disabled.png", true));
		// scroll_pane
		putIcon(key_icon_scroll_pane_lower_right_corner_icon, OnlyImageBox.getClassPathIconByName("scrollbar/scroll_pane_lower_right_corner_icon.png", true));

		// combo_box_button
		putIcon(key_icon_combo_box_button_arrow_normal, OnlyImageBox.getClassPathIconByName("combobox/combo_box_button_arrow_normal.png", true));
		putIcon(key_icon_combo_box_button_arrow_disabled, OnlyImageBox.getClassPathIconByName("combobox/combo_box_button_arrow_disabled.png", true));
		// menu
		putIcon(key_icon_menu_arrow, OnlyImageBox.getClassPathIconByName("menu/menu_arrow.png", true));
		// menu_item
		putIcon(key_icon_menu_radio_button_item_selected, OnlyImageBox.getClassPathIconByName("menu/menu_radio_button_item_selected.png", true));
		putIcon(key_icon_menu_check_box_item_selected, OnlyImageBox.getClassPathIconByName("menu/menu_check_box_item_selected.png", true));

		// spinner_previous
		putIcon(key_icon_spinner_previous_normal, OnlyImageBox.getClassPathIconByName("spinner/spinner_icon_previous_button_normal.png", true));
		putIcon(key_icon_spinner_previous_disabled, OnlyImageBox.getClassPathIconByName("spinner/spinner_icon_previous_button_disabled.png", true));
		// spinner_next
		putIcon(key_icon_spinner_next_normal, OnlyImageBox.getClassPathIconByName("spinner/spinner_icon_next_button_normal.png", true));
		putIcon(key_icon_spinner_next_disabled, OnlyImageBox.getClassPathIconByName("spinner/spinner_icon_next_button_disabled.png", true));
		// split_pane_touch
		putIcon(key_icon_split_pane_touch_left, OnlyImageBox.getClassPathIconByName("split/split_pane_touch_left.png", true));
		putIcon(key_icon_split_pane_touch_up, OnlyImageBox.getClassPathIconByName("split/split_pane_touch_up.png", true));
		putIcon(key_icon_split_pane_touch_right, OnlyImageBox.getClassPathIconByName("split/split_pane_touch_right.png", true));
		putIcon(key_icon_split_pane_touch_down, OnlyImageBox.getClassPathIconByName("split/split_pane_touch_down.png", true));
		// slider_thumb
		putIcon(key_icon_slider_thumb_h, OnlyImageBox.getClassPathIconByName("slider/slider_thumb_h.png", true));
		putIcon(key_icon_slider_thumb_v, OnlyImageBox.getClassPathIconByName("slider/slider_thumb_v.png", true));
		putIcon(key_icon_slider_min_thumb_h, OnlyImageBox.getClassPathIconByName("slider/slider_min_thumb_h.png", true));
		putIcon(key_icon_slider_min_thumb_v, OnlyImageBox.getClassPathIconByName("slider/slider_min_thumb_v.png", true));
		// Table
		putIcon(key_icon_table_header_up, OnlyImageBox.getClassPathIconByName("table/table_arrow_up.png", true));
		putIcon(key_icon_table_header_down, OnlyImageBox.getClassPathIconByName("table/table_arrow_down.png", true));
		// Tree
		putIcon(key_icon_tree_expanded, OnlyImageBox.getClassPathIconByName("tree/tree_expanded.png", true));
		putIcon(key_icon_tree_collapsed, OnlyImageBox.getClassPathIconByName("tree/tree_collapsed.png", true));
		putIcon(key_icon_tree_node_default, OnlyImageBox.getClassPathIconByName("tree/tree_node.png", true));
		// message_box
		putIcon(key_icon_message_box_error, OnlyImageBox.getClassPathIconByName("message/message_box_error.png", true));
		putIcon(key_icon_message_box_information, OnlyImageBox.getClassPathIconByName("message/message_box_infor.png", true));
		putIcon(key_icon_message_box_question, OnlyImageBox.getClassPathIconByName("message/message_box_question.png", true));
		putIcon(key_icon_message_box_warning, OnlyImageBox.getClassPathIconByName("message/message_box_warning.png", true));

		/******************************/
		putColor(KEY_EMPTY_COLOR, new Color(0, 0, 0, 0));
		putColor(KEY_WHITE_COLOR, new Color(254, 255, 255));
		// text

		putColor(key_color_text_not_editable_background, new Color(235, 235, 235));
		putColor(key_color_text_disabled_background, new Color(250, 250, 249));
		// password text
		putColor(key_color_password_not_editable_background, new Color(235, 235, 235));
		putColor(key_color_password_disabled_background, new Color(250, 250, 249));

		// formatted text

		putColor(key_color_formatted_text_not_editable_background, new Color(235, 235, 235));
		putColor(key_color_formatted_text_disabled_background, new Color(250, 250, 249));
		// scroll bar color
		putColor(key_color_scroll_bar_border, new Color(226, 238, 243));
		putColor(key_color_scroll_bar_background, new Color(235, 243, 246));
		// combo_box_text
		putColor(key_color_combo_box_text_selection_foreground, getColor(KEY_WHITE_COLOR));
		//
		putColor(key_color_combo_box_text_disabled_background, new Color(250, 250, 249));
		putColor(key_color_combo_box_text_not_editable_background, new Color(235, 235, 235));
		// combo_box_ Editor
		putColor(key_color_combo_box_editor_text_selection, new Color(49, 106, 197));
		putColor(key_color_combo_box_editor_text_selection_foregroun, getColor(KEY_WHITE_COLOR));
		// spinner_text
		putColor(key_color_spinner_text_selection_background, new Color(49, 106, 197));
		putColor(key_color_spinner_text_selection_foreground, new Color(235, 235, 235));
		//
		putColor(key_color_spinner_text_disabled_background, new Color(250, 250, 249));
		// slider
		putColor(key_color_slider_disabled_tick, Color.GRAY);
		// list
		putColor(key_color_list_disabled_background, new Color(250, 250, 249));
		// Table
		putColor(key_color_table_text_disabled_background, new Color(250, 250, 249));
		putColor(key_color_table_text_selection_foreground, getColor(KEY_WHITE_COLOR));
		putColor(key_color_table_text_selection, new Color(49, 106, 197));
		// ScrollList
		putColor(key_color_scroll_list_text_disabled_background, new Color(250, 250, 249));
		// scroll_table
		putColor(key_color_scroll_table_text_disabled_background, new Color(250, 250, 249));
		// scroll_text
		putColor(key_color_scroll_text_not_editable_background, new Color(235, 235, 235));
		putColor(key_color_scroll_text_disabled_background, new Color(250, 250, 249));
		// Tree
		putColor(key_color_tree_text_disabled_background, new Color(250, 250, 249));
		putColor(key_color_tree_line, new Color(172, 168, 153));
		putColor(key_color_tree_text_selection, new Color(49, 106, 197));
		// tree_column_text
		putColor(key_color_tree_column_text_selection, new Color(49, 106, 197));
		putColor(key_color_tree_column_text_selection_foreground, getColor(KEY_WHITE_COLOR));
		// calendar
		putColor(key_color_calendar_day_background, new Color(0, 128, 157));
		putColor(key_color_calendar_week_foreground, new Color(44, 74, 137));
		putColor(key_color_calendar_weekend_foreground, new Color(255, 128, 128));
		/********* border ************/

		// text_field
		putBorder(key_border_text_field_normal, new ImageBorder(OnlyImageBox.getClassPathImageByName("border/border_common_normal.png", true), 5, 6, 3, 4));
		putBorder(key_border_text_field_rollover, new ImageBorder(OnlyImageBox.getClassPathImageByName("border/border_common_rollover.png", true), 5, 6, 3, 4));
		putBorder(key_border_text_field_disabled, new ImageBorder(OnlyImageBox.getClassPathImageByName("border/border_common_disabled.png", true), 5, 6, 3, 4));
		putBorder(key_border_text_field_not_editable, new ImageBorder(OnlyImageBox.getClassPathImageByName("border/border_common_disabled.png", true), 5, 6, 3, 4));
		putBorder(key_border_text_field_not_editable_rollover, new ImageBorder(OnlyImageBox.getClassPathImageByName("border/border_common_rollover.png", true), 5, 6, 3, 4));

		// password_field
		putBorder(key_border_password_field_normal, new ImageBorder(OnlyImageBox.getClassPathImageByName("border/border_common_normal.png", true), 5, 6, 3, 4));
		putBorder(key_border_password_field_rollover, new ImageBorder(OnlyImageBox.getClassPathImageByName("border/border_common_rollover.png", true), 5, 6, 3, 4));
		putBorder(key_border_password_field_disabled, new ImageBorder(OnlyImageBox.getClassPathImageByName("border/border_common_disabled.png", true), 5, 6, 3, 4));
		putBorder(key_border_password_field_not_editable, new ImageBorder(OnlyImageBox.getClassPathImageByName("border/border_common_disabled.png", true), 5, 6, 3, 4));
		putBorder(key_border_password_field_not_editable_rollover, new ImageBorder(OnlyImageBox.getClassPathImageByName("border/border_common_rollover.png", true), 5, 6, 3, 4));

		// formatted_text_field
		putBorder(key_border_formatted_text_field_normal, new ImageBorder(OnlyImageBox.getClassPathImageByName("border/border_common_normal.png", true), 5, 6, 3, 4));
		putBorder(key_border_formatted_text_field_rollover, new ImageBorder(OnlyImageBox.getClassPathImageByName("border/border_common_rollover.png", true), 5, 6, 3, 4));
		putBorder(key_border_formatted_text_field_disabled, new ImageBorder(OnlyImageBox.getClassPathImageByName("border/border_common_disabled.png", true), 5, 6, 3, 4));
		putBorder(key_border_formatted_text_field_not_editable, new ImageBorder(OnlyImageBox.getClassPathImageByName("border/border_common_disabled.png", true), 5, 6, 3, 4));
		putBorder(key_border_formatted_text_field_not_editable_rollover, new ImageBorder(OnlyImageBox.getClassPathImageByName("border/border_common_rollover.png", true), 5, 6, 3, 4));

		// text_area
		putBorder(key_border_text_area_normal, new ImageBorder(OnlyImageBox.getClassPathImageByName("border/border_common_normal.png", true), 5, 6, 3, 4));
		putBorder(key_border_text_area_rollover, new ImageBorder(OnlyImageBox.getClassPathImageByName("border/border_common_rollover.png", true), 5, 6, 3, 4));
		putBorder(key_border_text_area_disabled, new ImageBorder(OnlyImageBox.getClassPathImageByName("border/border_common_disabled.png", true), 5, 6, 3, 4));
		putBorder(key_border_text_area_not_editable, new ImageBorder(OnlyImageBox.getClassPathImageByName("border/border_common_disabled.png", true), 5, 6, 3, 4));
		putBorder(key_border_text_area_not_editable_rollover, new ImageBorder(OnlyImageBox.getClassPathImageByName("border/border_common_rollover.png", true), 5, 6, 3, 4));

		// combo_box_renderer
		putBorder(key_border_combo_box_renderer_normal, new EmptyBorder(0, 5, 0, 0));
		putBorder(key_border_combo_box_renderer_selected, new EmptyBorder(0, 3, 0, 0));
		putBorder(key_border_combo_box_popup, new CompoundBorder(new LineBorder(new Color(0, 147, 209)), new EmptyBorder(1, 1, 1, 1)));
		// combo_box_ Editor
		putBorder(key_border_combo_box_editor, new EmptyBorder(2, 3, 0, 0));
		// spinner_editor
		putBorder(key_border_spinner_editor_normal, new ImageBorder(OnlyImageBox.getClassPathImageByName("combobox/combo_box_renderer_border_inner.png")));
		putBorder(key_border_spinner_editor_disabled, new ImageBorder(OnlyImageBox.getClassPathImageByName("combobox/combo_box_renderer_border_disabled.png"), 2, 3, 0, 0));
		// compound_text
		putBorder(key_border_compound_text_normal, new ImageBorder(OnlyImageBox.getClassPathImageByName("border/border_common_normal.png", true), 5, 6, 3, 4));
		putBorder(key_border_compound_text_rollover, new ImageBorder(OnlyImageBox.getClassPathImageByName("border/border_common_rollover.png", true), 5, 6, 3, 4));
		putBorder(key_border_compound_text_disabled, new ImageBorder(OnlyImageBox.getClassPathImageByName("border/border_common_disabled.png", true), 5, 6, 3, 4));
		// List
		putBorder(key_border_list_renderer, new EmptyBorder(0, 4, 0, 0));
		// Table
		putBorder(key_border_table_editor, new CompoundBorder(new ImageBorder(OnlyImageBox.getClassPathImageByName("table/table_editor_border.png"), 2, 2, 2, 2), new EmptyBorder(0, 1, 0, 1)));
		// CheckedComboBox
		putBorder(key_border_checked_combo_box_popup, new CompoundBorder(new LineBorder(new Color(0, 147, 209)), new EmptyBorder(1, 1, 1, 1)));
		// SCROLL_TEXT
		putBorder(key_border_scroll_text, new EmptyBorder(2, 3, 1, 1));

		putBorder(key_border_scroll_text_field_normal, new ImageBorder(OnlyImageBox.getClassPathImageByName("border/border_common_normal.png", true), 5, 6, 3, 4));
		putBorder(key_border_scroll_text_field_rollover, new ImageBorder(OnlyImageBox.getClassPathImageByName("border/border_common_rollover.png", true), 5, 6, 3, 4));
		putBorder(key_border_scroll_text_field_disabled, new ImageBorder(OnlyImageBox.getClassPathImageByName("border/border_common_disabled.png", true), 5, 6, 3, 4));
		putBorder(key_border_scroll_text_field_not_editable, new ImageBorder(OnlyImageBox.getClassPathImageByName("border/border_common_disabled.png", true), 5, 6, 3, 4));
		putBorder(key_border_scroll_text_field_not_editable_rollover, new ImageBorder(OnlyImageBox.getClassPathImageByName("border/border_common_rollover.png", true), 5, 6, 3, 4));
		// Tree
		putBorder(key_border_tree_renderer, new EmptyBorder(0, 3, 0, 1));
		putBorder(key_border_tree_editor, new CompoundBorder(new ImageBorder(OnlyImageBox.getClassPathImageByName("border/border_common_rollover.png", true), 2, 2, 2, 2), new EmptyBorder(0, 1, 0, 1)));
		// Tree_column_editor
		putBorder(key_border_tree_column_editor, new CompoundBorder(new ImageBorder(OnlyImageBox.getClassPathImageByName("border/border_common_rollover.png", true), 2, 2, 2, 2), new EmptyBorder(0, 1, 0, 1)));
		// calendar
		putBorder(key_border_calendar_day_normal, new EmptyBorder(2, 2, 2, 2));
		putBorder(key_border_calendar_day_selected, new LineBorder(new Color(21, 88, 152), 2));

	}

	private static void putImage(String key, Image image) {
		imageMap.put(key, image);
	}

	private static void putIcon(String key, Icon icon) {
		iconMap.put(key, icon);
	}

	private static void putColor(String key, Color color) {
		colorMap.put(key, color);
	}

	private static void putBorder(String key, Border border) {
		borderMap.put(key, border);
	}

	public static Image getImage(String key) {
		return imageMap.get(key);
	}

	public static Icon getIcon(String key) {
		return iconMap.get(key);
	}

	public static ImageIcon getImageIcon(String key) {
		return imageIconMap.get(key);
	}

	public static Color getColor(String key) {
		Color color = colorMap.get(key);
		return color == null ? colorMap.get(KEY_EMPTY_COLOR) : color;
	}

	public static Border getBorder(String key) {
		return borderMap.get(key);
	}

	public static Font getFont(String key) {
		return fontMap.get(key);
	}

	public static Map<String, Color> getAllColors() {
		return colorMap;
	}

	public static Map<String, Image> getAllImages() {
		return imageMap;
	}

	public static Map<String, Icon> getAllIcons() {
		return iconMap;
	}

	public static Color getEmptyColor() {
		return getColor(KEY_EMPTY_COLOR);
	}

	public static Color getWhiteColor() {
		return getColor(KEY_WHITE_COLOR);
	}
}
